import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;

public class OffsetVisitor extends ASTVisitor {

	private final int offset;

	public OffsetVisitor(int offset) {
		this.offset = offset;
	}

	@Override
	public boolean preVisit2(ASTNode node) {
		int offset = node.getStartPosition();
		int length = node.getLength();
		return (offset <= this.offset) && (this.offset < offset + length);
	}

}