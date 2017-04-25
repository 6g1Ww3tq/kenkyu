import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.IDocElement;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SimpleType;

public class ASTSample {

	public static void main(String[] args) {
		IDocElement document = new IDocElement() {
		};	// 変更対象のソースのIDocument
		IType type;        	// 変更対象のクラス
//		MethodDeclaration method = new MethodDeclaration();	// CompilationUnitから変更対象のメソッドを取得

		ASTParser parser = ASTParser.newParser(AST.JLS4);
//		parser.setSource(type.getCompilationUnit());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		CompilationUnit unitNode = (CompilationUnit) parser.createAST(new NullProgressMonitor());
		unitNode.recordModifications();

		AST ast = unitNode.getAST();

//		modifyMethod(method);

//		applyDocument(document, unitNode);

		/*
		ASTNode node; 

		MethodFindVisitor visitor = new MethodFindVisitor("メソッド名");
		node.accecpt(visitor);
		MethodDeclaration method = visitor.getFoundMethod();

		if (method != null) {
			System.out.printf("Javadoc   =%s%n", method.getJavadoc());
			System.out.printf("可視性等  =%s%n", method.modifiers());
			System.out.printf("戻り型    =%s%n", method.getReturnType2());
			System.out.printf("メソッド名=%s%n", method.getName().getIdentifier());
			System.out.printf("引数      =%s%n", method.parameters());
			System.out.printf("本体      =%s%n", method.getBody());
		}
		*/
	}

	private static void modifyMethod(MethodDeclaration method) {
		AST ast = method.getAST();

		// メソッド名を「example」に変える
		SimpleName name = ast.newSimpleName("example");
		method.setName(name);

		// 戻り型を「String」に変える
		SimpleType rtype = ast.newSimpleType(ast.newName("String"));
	}

	public static ASTNode getASTNode(ICompilationUnit unit) {
		ASTParser parser = ASTParser.newParser(AST.JLS4);
		parser.setSource(unit);
		ASTNode node = parser.createAST(new NullProgressMonitor());
		return node;
	}
}