package domain;

public class SteepDownArea {
	double mib;
	int startIndex;
	int endIndex;
	
	public SteepDownArea(int startIndex, double mib){
		this.startIndex = startIndex;
		this.mib = mib;
	}
	
	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public double getMib() {
		return mib;
	}

	public void setMib(double mib) {
		this.mib = mib;
	}
}
