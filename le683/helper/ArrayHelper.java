package le683.helper;

public class ArrayHelper {

	public static String[] addToList(String object, String[] array){
		String[] ret = new String[array.length+1];
		for(int i=0; i<array.length; i++){
			ret[i]=array[i];
		}
		ret[array.length]=object;
		return ret;
	}
	
	public static boolean isOnList(String object, String[] array){
		try{
			for(int i=0; i<array.length; i++){
				if(object.equals(array[i])){
					return true;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Objects aren't same type!");
		}
		return false;
	}
	
	public static String[] removeFromList(String object, String[] array){
		String[] ret = null;
		int remIndex = -1;
		try{
			for(int i=0; i<array.length; i++){
				if(object.equals(array[i])){
					remIndex = i;
					break;
				}
			}
			if(remIndex != -1){
				ret = new String[array.length-1];
				int ri=0;
				int wi=0;
				while(wi<ret.length){
					if(ri==remIndex){
						ri++;
					}else{
						ret[wi]=array[ri];
						ri++;
						wi++;
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Objects aren't same type");
		}
		return ret;
	}
}
