package model; 
public enum Resolution{
	SD(640,480),
	QHD1(960,540),
	HD(1280,720),
	FHD(1920,1080),
	QHD2(2560,1440),
	UHD(3840,2160),
	UHD8K(7640, 4320);
    private  int width;
	private  int height;
	 Resolution (int width, int height){
		this.width= width;
		this.height= height;
	}

	public int getwidth(){
		return width;
	}

		public int getheight(){
		return height;
	}
}

