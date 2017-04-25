import java.io.CharArrayReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

public class ASTSample2 {
	private static final int READ_ERROR = -1;

	public static void main(String[] args) {
		// Create AST Parser
		File f = new File("./src/Hoge.java");
		StringBuilder sb = new StringBuilder();
		int data;
		try {
			FileReader fr = new FileReader(f);
			while ((data = fr.read()) != READ_ERROR) {
				sb.append((char)data);
			}
		} catch ( IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		ASTParser parser = ASTParser.newParser(AST.JLS8);
		parser.setSource(sb.toString().toCharArray());
		CompilationUnit unit = (CompilationUnit) parser.createAST(new NullProgressMonitor());
		unit.accept(new MyVisitor());
	}

}
