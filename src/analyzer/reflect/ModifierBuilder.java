package analyzer.reflect;

import java.lang.reflect.Modifier;

public class ModifierBuilder {
	int modifier;
	StringBuilder sb;

	public ModifierBuilder(int modifier) {
		this.modifier = modifier;
		this.sb = new StringBuilder();
	}

	private void writeAbstract(){
		if (Modifier.isAbstract(modifier)) {
			sb.append("abstract ");
		}
	}

	private void writeFinal(){
		if (Modifier.isFinal(modifier)) {
			sb.append("final ");
		}
	}

	private void writePrivate(){
		if (Modifier.isPrivate(modifier)) {
			sb.append("private ");
		}
	}

	private void writeProtected(){
		if (Modifier.isProtected(modifier)) {
			sb.append("protected ");
		}
	}

	private void writePublic(){
		if (Modifier.isPublic(modifier)) {
			sb.append("public ");
		}
	}

	private void writeStatic(){
		if (Modifier.isStatic(modifier)) {
			sb.append("static ");
		}
	}

	@Override
	public String toString() {
		writeAbstract();
		writeFinal();
		writePrivate();
		writeProtected();
		writePublic();
		writeStatic();
		
		return sb.toString();
	}
}
