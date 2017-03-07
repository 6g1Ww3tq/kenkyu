import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * 階層数を指定して、ディレクトリ階層をWindowsのtreeコマンドと同じ形式で出力するクラスです。 ディレクトリだけでなくファイルも出力するかどうかの指定も可能です。
 */
public class Tree {
	private static final boolean CONTAIN_THUMBS_DB = true;
	private final Node root;
	private final int depth;
	private final boolean containsFile;
	private final PrintStream out;

	/**
	 * ディレクトリ階層を出力します。
	 *
	 * @param path 処理対象のディレクトリ
	 * @param depth 階層数(0の場合はすべて出力)
	 * @param containsFile ファイルも出力するかどうか
	 * @param outputPath 出力先ファイル(nullの場合は標準出力)
	 * @throws java.io.IOException ファイルI/Oに問題が発生した場合
	 */
	public static void tree(String path, int depth, boolean containsFile, String outputPath) throws IOException {
		new Tree(Paths.get(path).toRealPath(LinkOption.NOFOLLOW_LINKS), depth, containsFile, outputPath).process();
	}

	/**
	 * コンストラクタです。
	 *
	 * @param path 処理対象のディレクトリ
	 * @param depth 階層数
	 * @param outputFile ファイルも出力するかどうか
	 * @param outputPath 出力先ファイル(nullの場合は標準出力)
	 */
	private Tree(Path path, int depth, boolean containsFile, String outputPath) throws FileNotFoundException {
		this.root = new Node(path, true, null);
		this.depth = depth;
		this.containsFile = containsFile;
		this.out = (outputPath == null) ? System.out : new PrintStream(new File(outputPath));
	}

	/**
	 * 本処理を行います。
	 */
	private void process() throws IOException {
		Files.walkFileTree(root.path, new MyFileVisitor());
		sort(root);
		printOut(root);
	}

	private void sort(Node node) {
		Collections.sort(node.childs);
//		node.childs.forEach(this::sort);
	}

	/**
	 * ディレクトリを出力します。
	 *
	 * @param node 処理対象のディレクトリ
	 */
	private void printOut(Node node) {
		final StringBuilder sb = new StringBuilder(node.path.toString());
		Node p = node;
		if (p.parent != null) {
			sb.insert(0, p.isLastNode() ? "└" : "├");
			p = p.parent;
		}
		while (p.parent != null) {
			sb.insert(0, p.isLastNode() ? "  " : "｜");
			p = p.parent;
		}
		out.println(sb.toString());
		//printOut()の再帰的呼び出し
//		node.childs.forEach(this::printOut);
	}

	/**
	 * ディレクトリ/ファイル構成を表すクラスです。
	 */
	class Node implements Comparable<Node> {

		final Path path;
		final boolean dir;
		final Node parent;
		final List<Node> childs = new ArrayList<>();

		Node(Path path, boolean dir, Node parent) {
			this.path = path;
			this.dir = dir;
			this.parent = parent;
		}

		boolean isLastNode() {
			final int size = parent.childs.size();
			return parent.childs.get(size - 1) == this;
		}

		@Override
		public int compareTo(Node o2) {
			return (this.dir == true)
					? ((o2.dir == false) ? -1 : this.path.toString().compareTo(o2.path.toString()))
					: ((o2.dir == true) ? 1 : this.path.toString().compareTo(o2.path.toString()));
		}
	}

	/**
	 * ディレクトリ階層を辿ってMyPathを構築するクラスです。
	 */
	class MyFileVisitor extends SimpleFileVisitor<Path> {
		private final Stack<Node> parentNodeStack = new Stack<>(); //新Node追加先の親Nodeのスタック

		@Override
		public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
			final Path path = root.path.relativize(dir);
			final int count = root.path.equals(dir) ? 0 : path.getNameCount();
			if (depth > 0 && count > depth) {
				//深さが指定値を超える場合は処理しない
				return FileVisitResult.SKIP_SUBTREE;
			} else if (count > 0) {
				//ルート以外はNodeを生成する
				final Node node = new Node(path.getName(count - 1), true, parentNodeStack.peek());
				//親Nodeに新ノードを追加
				parentNodeStack.peek().childs.add(node);
				parentNodeStack.push(node);
			} else {
				//ルートである(count==0)
				parentNodeStack.push(root);
			}
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult postVisitDirectory(Path dir, IOException e) {
			parentNodeStack.pop();
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			if (containsFile == false) {
				//ファイル名を出力しない場合は処理しない
				return FileVisitResult.CONTINUE;
			} else if (CONTAIN_THUMBS_DB == false && file.getFileName().toString().equals("Thumbs.db")) {
				//Thumbs.dbを含めない
				return FileVisitResult.CONTINUE;
			}
			final Path path = root.path.relativize(file);
			final int count = path.getNameCount();
			final Node node = new Node(path.getName(count - 1), false, parentNodeStack.peek());
			//親Nodeに新ノードを追加
			parentNodeStack.peek().childs.add(node);
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult visitFileFailed(Path file, IOException ex) {
			System.err.println(ex);
			return FileVisitResult.SKIP_SUBTREE;
		}
	}
}