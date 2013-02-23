package structures.old;

public class Note implements Comparable<Note> {
	
	int halfStep;
	String chromaticValue;
	double freq; // Hz
	
	public int compareTo(Note n) {
		if (this.halfStep == n.getHalfStep()) {return 0;}
		else {return 1;}
		
	}
	
	public Note() {
		this(0,"",0.0);
	}
	
	public Note(int halfStep) {
		this(halfStep,"",0);
	}
	
	public Note(int halfStep, String chromaticValue, double freq) {
		this.halfStep = halfStep;
		this.chromaticValue = chromaticValue;
		this.freq = freq;	
	}

	public int getHalfStep() {
		return halfStep;
	}

	public void setHalfStep(int halfStep) {
		this.halfStep = halfStep;
	}

	public String getChromaticValue() {
		return chromaticValue;
	}

	public void setChromaticValue(String chromaticValue) {
		this.chromaticValue = chromaticValue;
	}

	public double getFreq() {
		return freq;
	}

	public void setFreq(double freq) {
		this.freq = freq;
	}
	
}
