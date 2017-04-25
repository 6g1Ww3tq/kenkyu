import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;

public class MyVisitor extends ASTVisitor {

	@Override
	public boolean visit(MethodDeclaration node) {
		// TODO 自動生成されたメソッド・スタブ
		System.out.println(node);
		return super.visit(node);
	}

	@Override
	public boolean visit(FieldDeclaration node) {
		// TODO 自動生成されたメソッド・スタブ
		System.out.println(node);
		return super.visit(node);
	}
}