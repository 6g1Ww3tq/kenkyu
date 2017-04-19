package window.tree;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class MyFileVisitor implements FileVisitor<Path> {

	public MyFileVisitor() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
		System.out.println("preVisitDirectory:"+dir.getFileName());
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		System.out.println("preVisitDirectory:"+file.getFileName());
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
		String error = String.format("[exception=%s,message=%s", exc.getClass(),exc.getMessage());
		System.out.println("visitFileFailed:"+file.getFileName() + error);
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
		System.out.println("postVisitDirectory:"+dir.getFileName());
		return FileVisitResult.CONTINUE;
	}

}
