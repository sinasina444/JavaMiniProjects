package Homework2;

public class PolylineEncoder {
	public String encodePolyline(Gps[] gpsPoints) {
		int len = gpsPoints.length;
		if(len==0 || gpsPoints==null){
		    return new String();
		}
		//save result in String buffer
		StringBuffer strRes = new StringBuffer();
		for(int i = 0;i<len;i++){
			//take initial latitude and longitude of gps
			//take the decimal value and multiply it by 100000,rounding the result
			//then calculate the offset value if it is not the first value
			int nLatitude;
			int nLongitude;
			if(i==0){
				nLatitude = (int)(gpsPoints[i].getLatitude()*100000);
				nLongitude = (int)(gpsPoints[i].getLongitude()*100000);
			}else{
				nLatitude = (int)(gpsPoints[i].getLatitude()*100000)-(int)(gpsPoints[i-1].getLatitude()*100000);
				nLongitude = (int)(gpsPoints[i].getLongitude()*100000)-(int)(gpsPoints[i-1].getLongitude()*100000);
			}
			
			//then transfer the number to string using Polyline Algorithm and add it to string buffer directly
			polylineNumToString(nLatitude,strRes);
			polylineNumToString(nLongitude,strRes);
		}	
		return strRes.toString();
	}
	
	//transfer the number to string using Polyline Algorithm
	public void polylineNumToString(int number,StringBuffer strRes){
		//use function processNumber
		//if it is negative,inverting the binary value and adding one to the last bit
		boolean bPos;
		if(number>=0){
			bPos = true;
		}else{
			bPos = false;
		}
		int nAbsNum = Math.abs(number);
		if(!bPos){
			nAbsNum = ~nAbsNum+1;
		}
		//Left-shift the binary value one bit
		nAbsNum = nAbsNum << 1;
		//If the original decimal value is negative, invert this encoding:
		if(!bPos){
			nAbsNum = ~nAbsNum;
		}
		//break the binary value out into 5-bit chunks and reverse order
		//OR each value with 0x20 if another bit chunk follows
		polylineChunkProcess(nAbsNum,strRes);
	}
	
	//transfer the absolute value of number to string using Polyline Algorithm
	public void polylineChunkProcess(int num,StringBuffer strbuf){
		int temp=0;
		int[] arr = new int[8]; //Integer data have 32 bits
		if(num==0){
			char c = (char)63;	
			strbuf.append(c);
			return;
		}
		int i =0;
		while(num!=0){
			//get the last 5 bits of num
			temp = num & 0b11111;
			num = num>>>5;	// fill 0 on the left side
			arr[i++] = temp;
		}
		//reverse order
		//OR each value with 0x20 if another bit chunk follows
		//i present the number of chunks
		for(int j=0;j<i;j++){
			if(j!=i-1){
				arr[j] = arr[j] | 0x20;
			}
			//add 63 to each value
			arr[j]+=63;
			//Convert each value to its ASCII equivalent,and add them to stringbuffer
			char ch = (char)arr[j];
			strbuf.append(ch);
		}
		return;
		
	}
}
