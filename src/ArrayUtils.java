public class ArrayUtils {
	
	public static double[] mult(double val, double[] array){
		
		for (int i = 0; i < array.length; i++) {
			array[i] = val * array[i];
		}
		
		return array;
	}
	
	public static double[] sum(double[] array1, double[] array2){
		
		int minlen = (array1.length > array2.length) ? array2.length : array1.length;
		
		double[] result = new double[minlen];
		
		for (int i = 0; i < minlen; i++) {
			result[i] = array1[i] + array2[i];
		}
		
		return result;
	}
	
	public static double[] dif(double[] array1, double[] array2){
		
		int minlen = (array1.length > array2.length) ? array2.length : array1.length;
		
		double[] result = new double[minlen];
		
		for (int i = 0; i < minlen; i++) {
			result[i] = array1[i] - array2[i];
		}
		
		return result;
	}
 
}