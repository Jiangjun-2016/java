package util;

import java.util.Arrays;

/**
 * @author fxf
 * @create 2017-09-15 10:33
 **/

public class ArraysDeep {

	public static void main(String[] args) {

		/**复制代码示例 开始*/

		//源数组
		int[] original = {7,5,1,9,14,11,19,33,21};
		//通过java.util.Arrays类的copyOf方法对源数组进行复制
		int[] dest = Arrays.copyOf(original, 4);
		for(int i = 0;i < dest.length;i++){
			System.out.print(dest[i] + " ");
		}
		System.out.println();
		System.out.println("---------------------");
		//通过java.util.Arrays类的copyOf方法对源数组进行复制（如果复制的长度超过了源数组的长度，则用0填充）
		int[] dest1 = Arrays.copyOf(original, 20);
		for(int i = 0;i < dest1.length;i++){
			System.out.print(dest1[i] + " ");
		}
		/**
		 * 运行结果
		 * 7 5 1 9
		 ---------------------
		 7 5 1 9 14 11 19 33 21 0 0 0 0 0 0 0 0 0 0 0
		 */
		/**复制代码示例 结束*/

	}
}
/**
 * 引用博客：http://blog.csdn.net/longlong2015/article/details/48179465
 * 源码解读
 */
/**
 * 说明：
 * （1）Arrays包含用来操作数组（比如排序和搜索）的各种方法。Arrays提供的方法都是静态方法，Arrays的构造函数是私有的，也就是不能被实例化。
 　（2）包含一个允许将数组作为列表来查看的静态工厂。
 　（3）除非特别注明，否则如果指定数组引用为 null，则此类中的方法都会抛出 NullPointerException。
 */
/**
 * 排序说明：
 * １＞　Java1.7之前的快排：在Java1.7之前的快排只是普通的快排，跟我们今天要研究的快排不一样，性能也差了许多，但其中对快排所做的各种优化我们依然是可以学习的。
 　　Java1.7的快排：Java1.7的快排是一种双轴快排，顾名思义：双轴快排是基于两个轴来进行比较，跟普通的选择一个点来作为轴点的快排是有很大的区别的。
 　２＞TimSort是mergeSort的一种改进，引入binarySort进行子数组的排序，实现优化（原来的子数组排序是采用的选择排序），
 		每次进行子数组合并的时候会进行一些特殊的处理来进行对一些特殊情况的优化。
 　　
 TimSort算法是一种起源于归并排序和插入排序的混合排序算法，设计初衷是为了在真实世界中的各种数据中可以有较好的性能。
 该算法最初是由Tim Peters于2002年在Python语言中提出的。
 　　
 TimSort 是一个归并排序做了大量优化的版本。对归并排序排在已经反向排好序的输入时表现O(n2)的特点做了特别优化。
 对已经正向排好序的输入减少回溯。对两种情况混合（一会升序，一会降序）的输入处理比较好。
 　　
 在jdk1.7之后，Arrays类中的sort方法有一个分支判断，当LegacyMergeSort.userRequested为true的情况下，采用legacyMergeSort，
 否则采用ComparableTimSort。并且在legacyMergeSort的注释上标明了该方法会在以后的jdk版本中废弃，
 因此以后Arrays类中的sort方法将采用ComparableTimSort类中的sort方法。
 */
//public class Arrays {
//
//	private static final int MIN_ARRAY_SORT_GRAN = 1 << 13;
//
//	private Arrays() {}//类的构造函数是私有的
//
//	static final class NaturalOrder implements Comparator<Object> {
//		@SuppressWarnings("unchecked")
//		public int compare(Object first, Object second) {
//			return ((Comparable<Object>)first).compareTo(second);
//		}
//		static final NaturalOrder INSTANCE = new NaturalOrder();
//	}
//
//	private static void rangeCheck(int arrayLength, int fromIndex, int toIndex) {
//		if (fromIndex > toIndex) {
//			throw new IllegalArgumentException(
//					"fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")");
//		}
//		if (fromIndex < 0) {
//			throw new ArrayIndexOutOfBoundsException(fromIndex);
//		}
//		if (toIndex > arrayLength) {
//			throw new ArrayIndexOutOfBoundsException(toIndex);
//		}
//	}
/**
 * 排序说明：
 * 重载方法列表：
 void sort (char[], int, int)
 void sort (byte[])
 void sort (byte[], int, int)
 void sort (T[], Comparator)
 void sort (short[], int, int)
 void sort (short)
 void sort (double[], int, int)
 void sort (Object[])
 void sort (Object[], int, int)
 void sort (T[], int, int, Comparator)
 void sort (double[])
 void sort (float[], int, int)
 void sort (float[])
 void sort (int[], int, int)
 void sort (long[])
 void sort (long[], int, int)
 void sort (long[])
 对于这些sort方法，我们可以从不同的角度分类：
 根据第一个参数的类型，可以将这些方法分为3类：基本数据类型数组的数值排序方法，提供了对于byte，short，int，long，char，float，double这些基本数据类型的数组的排序方法；自定义类的对象数组排序方法。提供了对于任意类类型个数组的排序方法，这些方法的输入参数为Object数组；泛型方法提供了泛型对象数组的排序实现。
 根据参数的个数，可以将这些方法分为3类：包含1个参数的方法对整个数组排序，包含3个参数的方法对数组的一部分进行排序，后两个参数确定了数组中要排序元素的范围；泛型方法中包含比较元素大小时使用的比较器类。
 根据实现的原理，可以将这些方法分为2类：数据数组排序方法采用一种结合了归并排序、插入排序、改进后的快速排序算法来实现，这些方法提供了以上7种基本数据类型的数组的排序；对象数组排序方法则。。。。。。。。。，这些方法提供了对象数组和泛型数组的排序算法的实现。


 *
 */
//整数数组的排序,升序排列，如果需要降序，则自行处理
//	public static void sort(int[] a) {
//		DualPivotQuicksort.sort(a, 0, a.length - 1, null, 0, 0);
//	}
//
//	public static void sort(int[] a, int fromIndex, int toIndex) {
//		rangeCheck(a.length, fromIndex, toIndex);
//		DualPivotQuicksort.sort(a, fromIndex, toIndex - 1, null, 0, 0);
//	}
//
//	public static void sort(long[] a) {
//		DualPivotQuicksort.sort(a, 0, a.length - 1, null, 0, 0);
//	}
//
//	public static void sort(long[] a, int fromIndex, int toIndex) {
//		rangeCheck(a.length, fromIndex, toIndex);
//		DualPivotQuicksort.sort(a, fromIndex, toIndex - 1, null, 0, 0);
//	}
//
//	public static void sort(short[] a) {
//		DualPivotQuicksort.sort(a, 0, a.length - 1, null, 0, 0);
//	}
//
//	public static void sort(short[] a, int fromIndex, int toIndex) {
//		rangeCheck(a.length, fromIndex, toIndex);
//		DualPivotQuicksort.sort(a, fromIndex, toIndex - 1, null, 0, 0);
//	}
//
//	public static void sort(char[] a) {
//		DualPivotQuicksort.sort(a, 0, a.length - 1, null, 0, 0);
//	}
//
//	public static void sort(char[] a, int fromIndex, int toIndex) {
//		rangeCheck(a.length, fromIndex, toIndex);
//		DualPivotQuicksort.sort(a, fromIndex, toIndex - 1, null, 0, 0);
//	}
//
//	public static void sort(byte[] a) {
//		DualPivotQuicksort.sort(a, 0, a.length - 1);
//	}
//
//	public static void sort(byte[] a, int fromIndex, int toIndex) {
//		rangeCheck(a.length, fromIndex, toIndex);
//		DualPivotQuicksort.sort(a, fromIndex, toIndex - 1);
//	}
//
//	public static void sort(float[] a) {
//		DualPivotQuicksort.sort(a, 0, a.length - 1, null, 0, 0);
//	}
//
//	public static void sort(float[] a, int fromIndex, int toIndex) {
//		rangeCheck(a.length, fromIndex, toIndex);
//		DualPivotQuicksort.sort(a, fromIndex, toIndex - 1, null, 0, 0);
//	}
//基本类型的排序都使用该排序算法
//	public static void sort(double[] a) {
//		DualPivotQuicksort.sort(a, 0, a.length - 1, null, 0, 0);
//	}
//
//	public static void sort(double[] a, int fromIndex, int toIndex) {
//		rangeCheck(a.length, fromIndex, toIndex);
//		DualPivotQuicksort.sort(a, fromIndex, toIndex - 1, null, 0, 0);
//	}
//
//	public static void parallelSort(byte[] a) {
//		int n = a.length, p, g;
//		if (n <= MIN_ARRAY_SORT_GRAN ||
//				(p = ForkJoinPool.getCommonPoolParallelism()) == 1)
//			DualPivotQuicksort.sort(a, 0, n - 1);
//		else
//			new ArraysParallelSortHelpers.FJByte.Sorter
//					(null, a, new byte[n], 0, n, 0,
//							((g = n / (p << 2)) <= MIN_ARRAY_SORT_GRAN) ?
//									MIN_ARRAY_SORT_GRAN : g).invoke();
//	}
//
//	public static void parallelSort(byte[] a, int fromIndex, int toIndex) {
//		rangeCheck(a.length, fromIndex, toIndex);
//		int n = toIndex - fromIndex, p, g;
//		if (n <= MIN_ARRAY_SORT_GRAN ||
//				(p = ForkJoinPool.getCommonPoolParallelism()) == 1)
//			DualPivotQuicksort.sort(a, fromIndex, toIndex - 1);
//		else
//			new ArraysParallelSortHelpers.FJByte.Sorter
//					(null, a, new byte[n], fromIndex, n, 0,
//							((g = n / (p << 2)) <= MIN_ARRAY_SORT_GRAN) ?
//									MIN_ARRAY_SORT_GRAN : g).invoke();
//	}
//
//	public static void parallelSort(char[] a) {
//		int n = a.length, p, g;
//		if (n <= MIN_ARRAY_SORT_GRAN ||
//				(p = ForkJoinPool.getCommonPoolParallelism()) == 1)
//			DualPivotQuicksort.sort(a, 0, n - 1, null, 0, 0);
//		else
//			new ArraysParallelSortHelpers.FJChar.Sorter
//					(null, a, new char[n], 0, n, 0,
//							((g = n / (p << 2)) <= MIN_ARRAY_SORT_GRAN) ?
//									MIN_ARRAY_SORT_GRAN : g).invoke();
//	}
//
//	public static void parallelSort(char[] a, int fromIndex, int toIndex) {
//		rangeCheck(a.length, fromIndex, toIndex);
//		int n = toIndex - fromIndex, p, g;
//		if (n <= MIN_ARRAY_SORT_GRAN ||
//				(p = ForkJoinPool.getCommonPoolParallelism()) == 1)
//			DualPivotQuicksort.sort(a, fromIndex, toIndex - 1, null, 0, 0);
//		else
//			new ArraysParallelSortHelpers.FJChar.Sorter
//					(null, a, new char[n], fromIndex, n, 0,
//							((g = n / (p << 2)) <= MIN_ARRAY_SORT_GRAN) ?
//									MIN_ARRAY_SORT_GRAN : g).invoke();
//	}
//
//	public static void parallelSort(short[] a) {
//		int n = a.length, p, g;
//		if (n <= MIN_ARRAY_SORT_GRAN ||
//				(p = ForkJoinPool.getCommonPoolParallelism()) == 1)
//			DualPivotQuicksort.sort(a, 0, n - 1, null, 0, 0);
//		else
//			new ArraysParallelSortHelpers.FJShort.Sorter
//					(null, a, new short[n], 0, n, 0,
//							((g = n / (p << 2)) <= MIN_ARRAY_SORT_GRAN) ?
//									MIN_ARRAY_SORT_GRAN : g).invoke();
//	}
//
//	public static void parallelSort(short[] a, int fromIndex, int toIndex) {
//		rangeCheck(a.length, fromIndex, toIndex);
//		int n = toIndex - fromIndex, p, g;
//		if (n <= MIN_ARRAY_SORT_GRAN ||
//				(p = ForkJoinPool.getCommonPoolParallelism()) == 1)
//			DualPivotQuicksort.sort(a, fromIndex, toIndex - 1, null, 0, 0);
//		else
//			new ArraysParallelSortHelpers.FJShort.Sorter
//					(null, a, new short[n], fromIndex, n, 0,
//							((g = n / (p << 2)) <= MIN_ARRAY_SORT_GRAN) ?
//									MIN_ARRAY_SORT_GRAN : g).invoke();
//	}
//
//	public static void parallelSort(int[] a) {
//		int n = a.length, p, g;
//		if (n <= MIN_ARRAY_SORT_GRAN ||
//				(p = ForkJoinPool.getCommonPoolParallelism()) == 1)
//			DualPivotQuicksort.sort(a, 0, n - 1, null, 0, 0);
//		else
//			new ArraysParallelSortHelpers.FJInt.Sorter
//					(null, a, new int[n], 0, n, 0,
//							((g = n / (p << 2)) <= MIN_ARRAY_SORT_GRAN) ?
//									MIN_ARRAY_SORT_GRAN : g).invoke();
//	}
//
//	public static void parallelSort(int[] a, int fromIndex, int toIndex) {
//		rangeCheck(a.length, fromIndex, toIndex);
//		int n = toIndex - fromIndex, p, g;
//		if (n <= MIN_ARRAY_SORT_GRAN ||
//				(p = ForkJoinPool.getCommonPoolParallelism()) == 1)
//			DualPivotQuicksort.sort(a, fromIndex, toIndex - 1, null, 0, 0);
//		else
//			new ArraysParallelSortHelpers.FJInt.Sorter
//					(null, a, new int[n], fromIndex, n, 0,
//							((g = n / (p << 2)) <= MIN_ARRAY_SORT_GRAN) ?
//									MIN_ARRAY_SORT_GRAN : g).invoke();
//	}
//
//	public static void parallelSort(long[] a) {
//		int n = a.length, p, g;
//		if (n <= MIN_ARRAY_SORT_GRAN ||
//				(p = ForkJoinPool.getCommonPoolParallelism()) == 1)
//			DualPivotQuicksort.sort(a, 0, n - 1, null, 0, 0);
//		else
//			new ArraysParallelSortHelpers.FJLong.Sorter
//					(null, a, new long[n], 0, n, 0,
//							((g = n / (p << 2)) <= MIN_ARRAY_SORT_GRAN) ?
//									MIN_ARRAY_SORT_GRAN : g).invoke();
//	}
//
//	public static void parallelSort(long[] a, int fromIndex, int toIndex) {
//		rangeCheck(a.length, fromIndex, toIndex);
//		int n = toIndex - fromIndex, p, g;
//		if (n <= MIN_ARRAY_SORT_GRAN ||
//				(p = ForkJoinPool.getCommonPoolParallelism()) == 1)
//			DualPivotQuicksort.sort(a, fromIndex, toIndex - 1, null, 0, 0);
//		else
//			new ArraysParallelSortHelpers.FJLong.Sorter
//					(null, a, new long[n], fromIndex, n, 0,
//							((g = n / (p << 2)) <= MIN_ARRAY_SORT_GRAN) ?
//									MIN_ARRAY_SORT_GRAN : g).invoke();
//	}
//
//	public static void parallelSort(float[] a) {
//		int n = a.length, p, g;
//		if (n <= MIN_ARRAY_SORT_GRAN ||
//				(p = ForkJoinPool.getCommonPoolParallelism()) == 1)
//			DualPivotQuicksort.sort(a, 0, n - 1, null, 0, 0);
//		else
//			new ArraysParallelSortHelpers.FJFloat.Sorter
//					(null, a, new float[n], 0, n, 0,
//							((g = n / (p << 2)) <= MIN_ARRAY_SORT_GRAN) ?
//									MIN_ARRAY_SORT_GRAN : g).invoke();
//	}
//
//	public static void parallelSort(float[] a, int fromIndex, int toIndex) {
//		rangeCheck(a.length, fromIndex, toIndex);
//		int n = toIndex - fromIndex, p, g;
//		if (n <= MIN_ARRAY_SORT_GRAN ||
//				(p = ForkJoinPool.getCommonPoolParallelism()) == 1)
//			DualPivotQuicksort.sort(a, fromIndex, toIndex - 1, null, 0, 0);
//		else
//			new ArraysParallelSortHelpers.FJFloat.Sorter
//					(null, a, new float[n], fromIndex, n, 0,
//							((g = n / (p << 2)) <= MIN_ARRAY_SORT_GRAN) ?
//									MIN_ARRAY_SORT_GRAN : g).invoke();
//	}
//
//	public static void parallelSort(double[] a) {
//		int n = a.length, p, g;
//		if (n <= MIN_ARRAY_SORT_GRAN ||
//				(p = ForkJoinPool.getCommonPoolParallelism()) == 1)
//			DualPivotQuicksort.sort(a, 0, n - 1, null, 0, 0);
//		else
//			new ArraysParallelSortHelpers.FJDouble.Sorter
//					(null, a, new double[n], 0, n, 0,
//							((g = n / (p << 2)) <= MIN_ARRAY_SORT_GRAN) ?
//									MIN_ARRAY_SORT_GRAN : g).invoke();
//	}
//
//	public static void parallelSort(double[] a, int fromIndex, int toIndex) {
//		rangeCheck(a.length, fromIndex, toIndex);
//		int n = toIndex - fromIndex, p, g;
//		if (n <= MIN_ARRAY_SORT_GRAN ||
//				(p = ForkJoinPool.getCommonPoolParallelism()) == 1)
//			DualPivotQuicksort.sort(a, fromIndex, toIndex - 1, null, 0, 0);
//		else
//			new ArraysParallelSortHelpers.FJDouble.Sorter
//					(null, a, new double[n], fromIndex, n, 0,
//							((g = n / (p << 2)) <= MIN_ARRAY_SORT_GRAN) ?
//									MIN_ARRAY_SORT_GRAN : g).invoke();
//	}
//
//	@SuppressWarnings("unchecked")
//	public static <T extends Comparable<? super T>> void parallelSort(T[] a) {
//		int n = a.length, p, g;
//		if (n <= MIN_ARRAY_SORT_GRAN ||
//				(p = ForkJoinPool.getCommonPoolParallelism()) == 1)
//			TimSort.sort(a, 0, n, NaturalOrder.INSTANCE, null, 0, 0);
//		else
//			new ArraysParallelSortHelpers.FJObject.Sorter<T>
//					(null, a,
//							(T[]) Array.newInstance(a.getClass().getComponentType(), n),
//							0, n, 0, ((g = n / (p << 2)) <= MIN_ARRAY_SORT_GRAN) ?
//							MIN_ARRAY_SORT_GRAN : g, NaturalOrder.INSTANCE).invoke();
//	}
//
//	@SuppressWarnings("unchecked")
//	public static <T extends Comparable<? super T>>
//	void parallelSort(T[] a, int fromIndex, int toIndex) {
//		rangeCheck(a.length, fromIndex, toIndex);
//		int n = toIndex - fromIndex, p, g;
//		if (n <= MIN_ARRAY_SORT_GRAN ||
//				(p = ForkJoinPool.getCommonPoolParallelism()) == 1)
//			TimSort.sort(a, fromIndex, toIndex, NaturalOrder.INSTANCE, null, 0, 0);
//		else
//			new ArraysParallelSortHelpers.FJObject.Sorter<T>
//					(null, a,
//							(T[])Array.newInstance(a.getClass().getComponentType(), n),
//							fromIndex, n, 0, ((g = n / (p << 2)) <= MIN_ARRAY_SORT_GRAN) ?
//							MIN_ARRAY_SORT_GRAN : g, NaturalOrder.INSTANCE).invoke();
//	}
//
//	@SuppressWarnings("unchecked")
//	public static <T> void parallelSort(T[] a, Comparator<? super T> cmp) {
//		if (cmp == null)
//			cmp = NaturalOrder.INSTANCE;
//		int n = a.length, p, g;
//		if (n <= MIN_ARRAY_SORT_GRAN ||
//				(p = ForkJoinPool.getCommonPoolParallelism()) == 1)
//			TimSort.sort(a, 0, n, cmp, null, 0, 0);
//		else
//			new ArraysParallelSortHelpers.FJObject.Sorter<T>
//					(null, a,
//							(T[])Array.newInstance(a.getClass().getComponentType(), n),
//							0, n, 0, ((g = n / (p << 2)) <= MIN_ARRAY_SORT_GRAN) ?
//							MIN_ARRAY_SORT_GRAN : g, cmp).invoke();
//	}
//
//	@SuppressWarnings("unchecked")
//	public static <T> void parallelSort(T[] a, int fromIndex, int toIndex,
//										Comparator<? super T> cmp) {
//		rangeCheck(a.length, fromIndex, toIndex);
//		if (cmp == null)
//			cmp = NaturalOrder.INSTANCE;
//		int n = toIndex - fromIndex, p, g;
//		if (n <= MIN_ARRAY_SORT_GRAN ||
//				(p = ForkJoinPool.getCommonPoolParallelism()) == 1)
//			TimSort.sort(a, fromIndex, toIndex, cmp, null, 0, 0);
//		else
//			new ArraysParallelSortHelpers.FJObject.Sorter<T>
//					(null, a,
//							(T[])Array.newInstance(a.getClass().getComponentType(), n),
//							fromIndex, n, 0, ((g = n / (p << 2)) <= MIN_ARRAY_SORT_GRAN) ?
//							MIN_ARRAY_SORT_GRAN : g, cmp).invoke();
//	}
//
//	static final class LegacyMergeSort {
//		private static final boolean userRequested =
//				java.security.AccessController.doPrivileged(
//						new sun.security.action.GetBooleanAction(
//								"java.util.Arrays.useLegacyMergeSort")).booleanValue();
//	}
//对象的排序，对象必须是可比较的，即必须实现Compareable接口
//	public static void sort(Object[] a) {
//		if (LegacyMergeSort.userRequested)
//			legacyMergeSort(a);
//		else
//			ComparableTimSort.sort(a, 0, a.length, null, 0, 0);
//	}
//
//	private static void legacyMergeSort(Object[] a) {
//		Object[] aux = a.clone();
//		mergeSort(aux, a, 0, a.length, 0);
//	}
//
//	public static void sort(Object[] a, int fromIndex, int toIndex) {
//		rangeCheck(a.length, fromIndex, toIndex);
//		if (LegacyMergeSort.userRequested)
//			legacyMergeSort(a, fromIndex, toIndex);
//		else
//			ComparableTimSort.sort(a, fromIndex, toIndex, null, 0, 0);
//	}
//
//	/** To be removed in a future release. */
//	private static void legacyMergeSort(Object[] a,
//										int fromIndex, int toIndex) {
//		Object[] aux = copyOfRange(a, fromIndex, toIndex);
//		mergeSort(aux, a, fromIndex, toIndex, -fromIndex);
//	}
//
//	private static final int INSERTIONSORT_THRESHOLD = 7;
//
//合并排序算法的实现，需额外的dest空间
//	@SuppressWarnings({"unchecked", "rawtypes"})
//	private static void mergeSort(Object[] src,
//								  Object[] dest,
//								  int low,
//								  int high,
//								  int off) {
//		int length = high - low;
//		//在小数据集上面之间用插入排序算法，即长度小于7时
//		if (length < INSERTIONSORT_THRESHOLD) {
//			for (int i=low; i<high; i++)
//				for (int j=i; j>low &&
//						((Comparable) dest[j-1]).compareTo(dest[j])>0; j--)//用Comparable的排序接口
//					swap(dest, j, j-1);//交换两个元素
//			return;
//		}
//		//元素长度大于7时，按合并排序算法进行排序
//		int destLow  = low;
//		int destHigh = high;
//		low  += off;
//		high += off;
//		int mid = (low + high) >>> 1;
//		从low到mid的元素进行合并排序
//		mergeSort(dest, src, low, mid, -off);
//		从mid到high的元素进行合并排序
//		mergeSort(dest, src, mid, high, -off);
//		 //如果两部分已经有序，则执行合并即可
//		if (((Comparable)src[mid-1]).compareTo(src[mid]) <= 0) {
//			System.arraycopy(src, low, dest, destLow, length);
//			return;
//		}
//		//进行合并操作
//		for(int i = destLow, p = low, q = mid; i < destHigh; i++) {
//			if (q >= high || p < mid && ((Comparable)src[p]).compareTo(src[q])<=0)
//				dest[i] = src[p++];
//			else
//				dest[i] = src[q++];
//		}
//	}
//
//	private static void swap(Object[] x, int a, int b) {
//		Object t = x[a];
//		x[a] = x[b];
//		x[b] = t;
//	}
//
//	public static <T> void sort(T[] a, Comparator<? super T> c) {
//		if (c == null) {
//			sort(a);
//		} else {
//			if (LegacyMergeSort.userRequested)
//				legacyMergeSort(a, c);
//			else
//				TimSort.sort(a, 0, a.length, c, null, 0, 0);
//		}
//	}
//
//	/** To be removed in a future release. */
//	private static <T> void legacyMergeSort(T[] a, Comparator<? super T> c) {
//		T[] aux = a.clone();
//		if (c==null)
//			mergeSort(aux, a, 0, a.length, 0);
//		else
//			mergeSort(aux, a, 0, a.length, 0, c);
//	}
//
//	public static <T> void sort(T[] a, int fromIndex, int toIndex,
//								Comparator<? super T> c) {
//		if (c == null) {
//			sort(a, fromIndex, toIndex);
//		} else {
//			rangeCheck(a.length, fromIndex, toIndex);
//			if (LegacyMergeSort.userRequested)
//				legacyMergeSort(a, fromIndex, toIndex, c);
//			else
//				TimSort.sort(a, fromIndex, toIndex, c, null, 0, 0);
//		}
//	}
//
//	/** To be removed in a future release. */
//	private static <T> void legacyMergeSort(T[] a, int fromIndex, int toIndex,
//											Comparator<? super T> c) {
//		T[] aux = copyOfRange(a, fromIndex, toIndex);
//		if (c==null)
//			mergeSort(aux, a, fromIndex, toIndex, -fromIndex);
//		else
//			mergeSort(aux, a, fromIndex, toIndex, -fromIndex, c);
//	}
//
//	@SuppressWarnings({"rawtypes", "unchecked"})
//	private static void mergeSort(Object[] src,
//								  Object[] dest,
//								  int low, int high, int off,
//								  Comparator c) {
//		int length = high - low;
//
//		// Insertion sort on smallest arrays
//		if (length < INSERTIONSORT_THRESHOLD) {
//			for (int i=low; i<high; i++)
//				for (int j=i; j>low && c.compare(dest[j-1], dest[j])>0; j--)
//					swap(dest, j, j-1);
//			return;
//		}
//
//		// Recursively sort halves of dest into src
//		int destLow  = low;
//		int destHigh = high;
//		low  += off;
//		high += off;
//		int mid = (low + high) >>> 1;
//		mergeSort(dest, src, low, mid, -off, c);
//		mergeSort(dest, src, mid, high, -off, c);
//
//		// If list is already sorted, just copy from src to dest.  This is an
//		// optimization that results in faster sorts for nearly ordered lists.
//		if (c.compare(src[mid-1], src[mid]) <= 0) {
//			System.arraycopy(src, low, dest, destLow, length);
//			return;
//		}
//
//		// Merge sorted halves (now in src) into dest
//		for(int i = destLow, p = low, q = mid; i < destHigh; i++) {
//			if (q >= high || p < mid && c.compare(src[p], src[q]) <= 0)
//				dest[i] = src[p++];
//			else
//				dest[i] = src[q++];
//		}
//	}
//
//	// Parallel prefix
//
//	public static <T> void parallelPrefix(T[] array, BinaryOperator<T> op) {
//		Objects.requireNonNull(op);
//		if (array.length > 0)
//			new ArrayPrefixHelpers.CumulateTask<>
//					(null, op, array, 0, array.length).invoke();
//	}
//
//	public static <T> void parallelPrefix(T[] array, int fromIndex,
//										  int toIndex, BinaryOperator<T> op) {
//		Objects.requireNonNull(op);
//		rangeCheck(array.length, fromIndex, toIndex);
//		if (fromIndex < toIndex)
//			new ArrayPrefixHelpers.CumulateTask<>
//					(null, op, array, fromIndex, toIndex).invoke();
//	}
//
//	public static void parallelPrefix(long[] array, LongBinaryOperator op) {
//		Objects.requireNonNull(op);
//		if (array.length > 0)
//			new ArrayPrefixHelpers.LongCumulateTask
//					(null, op, array, 0, array.length).invoke();
//	}
//
//	public static void parallelPrefix(long[] array, int fromIndex,
//									  int toIndex, LongBinaryOperator op) {
//		Objects.requireNonNull(op);
//		rangeCheck(array.length, fromIndex, toIndex);
//		if (fromIndex < toIndex)
//			new ArrayPrefixHelpers.LongCumulateTask
//					(null, op, array, fromIndex, toIndex).invoke();
//	}
//
//	public static void parallelPrefix(double[] array, DoubleBinaryOperator op) {
//		Objects.requireNonNull(op);
//		if (array.length > 0)
//			new ArrayPrefixHelpers.DoubleCumulateTask
//					(null, op, array, 0, array.length).invoke();
//	}
//
//	public static void parallelPrefix(double[] array, int fromIndex,
//									  int toIndex, DoubleBinaryOperator op) {
//		Objects.requireNonNull(op);
//		rangeCheck(array.length, fromIndex, toIndex);
//		if (fromIndex < toIndex)
//			new ArrayPrefixHelpers.DoubleCumulateTask
//					(null, op, array, fromIndex, toIndex).invoke();
//	}
//
//	public static void parallelPrefix(int[] array, IntBinaryOperator op) {
//		Objects.requireNonNull(op);
//		if (array.length > 0)
//			new ArrayPrefixHelpers.IntCumulateTask
//					(null, op, array, 0, array.length).invoke();
//	}
//
//	public static void parallelPrefix(int[] array, int fromIndex,
//									  int toIndex, IntBinaryOperator op) {
//		Objects.requireNonNull(op);
//		rangeCheck(array.length, fromIndex, toIndex);
//		if (fromIndex < toIndex)
//			new ArrayPrefixHelpers.IntCumulateTask
//					(null, op, array, fromIndex, toIndex).invoke();
//	}
//
//二分搜索算法，要求a是有序的
//	public static int binarySearch(long[] a, long key) {
//		return binarySearch0(a, 0, a.length, key);
//	}
//
//	public static int binarySearch(long[] a, int fromIndex, int toIndex,
//								   long key) {
//		rangeCheck(a.length, fromIndex, toIndex);
//		return binarySearch0(a, fromIndex, toIndex, key);
//	}
//执行二分搜索
//	private static int binarySearch0(long[] a, int fromIndex, int toIndex,
//									 long key) {
//		int low = fromIndex;
//		int high = toIndex - 1;
//
//		while (low <= high) {
//			int mid = (low + high) >>> 1;//用移位运算实现除法
//			long midVal = a[mid];
//
//			if (midVal < key)
//				low = mid + 1;
//			else if (midVal > key)
//				high = mid - 1;
//			else
//				return mid; // key found
//		}
//		return -(low + 1);  // key not found.
//	}
//
//	public static int binarySearch(int[] a, int key) {
//		return binarySearch0(a, 0, a.length, key);
//	}
//
//	public static int binarySearch(int[] a, int fromIndex, int toIndex,
//								   int key) {
//		rangeCheck(a.length, fromIndex, toIndex);
//		return binarySearch0(a, fromIndex, toIndex, key);
//	}
//
//	private static int binarySearch0(int[] a, int fromIndex, int toIndex,
//									 int key) {
//		int low = fromIndex;
//		int high = toIndex - 1;
//
//		while (low <= high) {
//			int mid = (low + high) >>> 1;
//			int midVal = a[mid];
//
//			if (midVal < key)
//				low = mid + 1;
//			else if (midVal > key)
//				high = mid - 1;
//			else
//				return mid; // key found
//		}
//		return -(low + 1);  // key not found.
//	}
//
//	public static int binarySearch(short[] a, short key) {
//		return binarySearch0(a, 0, a.length, key);
//	}
//
//	public static int binarySearch(short[] a, int fromIndex, int toIndex,
//								   short key) {
//		rangeCheck(a.length, fromIndex, toIndex);
//		return binarySearch0(a, fromIndex, toIndex, key);
//	}
//
//	private static int binarySearch0(short[] a, int fromIndex, int toIndex,
//									 short key) {
//		int low = fromIndex;
//		int high = toIndex - 1;
//
//		while (low <= high) {
//			int mid = (low + high) >>> 1;
//			short midVal = a[mid];
//
//			if (midVal < key)
//				low = mid + 1;
//			else if (midVal > key)
//				high = mid - 1;
//			else
//				return mid; // key found
//		}
//		return -(low + 1);  // key not found.
//	}
//
//	public static int binarySearch(char[] a, char key) {
//		return binarySearch0(a, 0, a.length, key);
//	}
//
//	public static int binarySearch(char[] a, int fromIndex, int toIndex,
//								   char key) {
//		rangeCheck(a.length, fromIndex, toIndex);
//		return binarySearch0(a, fromIndex, toIndex, key);
//	}
//
//	private static int binarySearch0(char[] a, int fromIndex, int toIndex,
//									 char key) {
//		int low = fromIndex;
//		int high = toIndex - 1;
//
//		while (low <= high) {
//			int mid = (low + high) >>> 1;
//			char midVal = a[mid];
//
//			if (midVal < key)
//				low = mid + 1;
//			else if (midVal > key)
//				high = mid - 1;
//			else
//				return mid; // key found
//		}
//		return -(low + 1);  // key not found.
//	}
//
//	public static int binarySearch(byte[] a, byte key) {
//		return binarySearch0(a, 0, a.length, key);
//	}
//
//	public static int binarySearch(byte[] a, int fromIndex, int toIndex,
//								   byte key) {
//		rangeCheck(a.length, fromIndex, toIndex);
//		return binarySearch0(a, fromIndex, toIndex, key);
//	}
//
//	private static int binarySearch0(byte[] a, int fromIndex, int toIndex,
//									 byte key) {
//		int low = fromIndex;
//		int high = toIndex - 1;
//
//		while (low <= high) {
//			int mid = (low + high) >>> 1;
//			byte midVal = a[mid];
//
//			if (midVal < key)
//				low = mid + 1;
//			else if (midVal > key)
//				high = mid - 1;
//			else
//				return mid; // key found
//		}
//		return -(low + 1);  // key not found.
//	}
//
//	public static int binarySearch(double[] a, double key) {
//		return binarySearch0(a, 0, a.length, key);
//	}
//
//	public static int binarySearch(double[] a, int fromIndex, int toIndex,
//								   double key) {
//		rangeCheck(a.length, fromIndex, toIndex);
//		return binarySearch0(a, fromIndex, toIndex, key);
//	}
//
//	private static int binarySearch0(double[] a, int fromIndex, int toIndex,
//									 double key) {
//		int low = fromIndex;
//		int high = toIndex - 1;
//
//		while (low <= high) {
//			int mid = (low + high) >>> 1;
//			double midVal = a[mid];
//
//			if (midVal < key)
//				low = mid + 1;  // Neither val is NaN, thisVal is smaller
//			else if (midVal > key)
//				high = mid - 1; // Neither val is NaN, thisVal is larger
//			else {
//				long midBits = Double.doubleToLongBits(midVal);
//				long keyBits = Double.doubleToLongBits(key);
//				if (midBits == keyBits)     // Values are equal
//					return mid;             // Key found
//				else if (midBits < keyBits) // (-0.0, 0.0) or (!NaN, NaN)
//					low = mid + 1;
//				else                        // (0.0, -0.0) or (NaN, !NaN)
//					high = mid - 1;
//			}
//		}
//		return -(low + 1);  // key not found.
//	}
//
//	public static int binarySearch(float[] a, float key) {
//		return binarySearch0(a, 0, a.length, key);
//	}
//
//	public static int binarySearch(float[] a, int fromIndex, int toIndex,
//								   float key) {
//		rangeCheck(a.length, fromIndex, toIndex);
//		return binarySearch0(a, fromIndex, toIndex, key);
//	}
//
//	private static int binarySearch0(float[] a, int fromIndex, int toIndex,
//									 float key) {
//		int low = fromIndex;
//		int high = toIndex - 1;
//
//		while (low <= high) {
//			int mid = (low + high) >>> 1;
//			float midVal = a[mid];
//
//			if (midVal < key)
//				low = mid + 1;  // Neither val is NaN, thisVal is smaller
//			else if (midVal > key)
//				high = mid - 1; // Neither val is NaN, thisVal is larger
//			else {
//				int midBits = Float.floatToIntBits(midVal);
//				int keyBits = Float.floatToIntBits(key);
//				if (midBits == keyBits)     // Values are equal
//					return mid;             // Key found
//				else if (midBits < keyBits) // (-0.0, 0.0) or (!NaN, NaN)
//					low = mid + 1;
//				else                        // (0.0, -0.0) or (NaN, !NaN)
//					high = mid - 1;
//			}
//		}
//		return -(low + 1);  // key not found.
//	}
//
//	public static int binarySearch(Object[] a, Object key) {
//		return binarySearch0(a, 0, a.length, key);
//	}
//
//	public static int binarySearch(Object[] a, int fromIndex, int toIndex,
//								   Object key) {
//		rangeCheck(a.length, fromIndex, toIndex);
//		return binarySearch0(a, fromIndex, toIndex, key);
//	}
//对象的二分搜索
//	private static int binarySearch0(Object[] a, int fromIndex, int toIndex,
//									 Object key) {
//		int low = fromIndex;
//		int high = toIndex - 1;
//
//		while (low <= high) {
//			int mid = (low + high) >>> 1;
//			@SuppressWarnings("rawtypes")
//			Comparable midVal = (Comparable)a[mid];
//			@SuppressWarnings("unchecked")
//			int cmp = midVal.compareTo(key);//用Compareable的排序接口
//
//			if (cmp < 0)
//				low = mid + 1;
//			else if (cmp > 0)
//				high = mid - 1;
//			else
//				return mid; // key found
//		}
//		return -(low + 1);  // key not found.
//	}
//
//	public static <T> int binarySearch(T[] a, T key, Comparator<? super T> c) {
//		return binarySearch0(a, 0, a.length, key, c);
//	}
//
//	public static <T> int binarySearch(T[] a, int fromIndex, int toIndex,
//									   T key, Comparator<? super T> c) {
//		rangeCheck(a.length, fromIndex, toIndex);
//		return binarySearch0(a, fromIndex, toIndex, key, c);
//	}
//
//	// Like public version, but without range checks.
//	private static <T> int binarySearch0(T[] a, int fromIndex, int toIndex,
//										 T key, Comparator<? super T> c) {
//		if (c == null) {
//			return binarySearch0(a, fromIndex, toIndex, key);
//		}
//		int low = fromIndex;
//		int high = toIndex - 1;
//
//		while (low <= high) {
//			int mid = (low + high) >>> 1;
//			T midVal = a[mid];
//			int cmp = c.compare(midVal, key);
//			if (cmp < 0)
//				low = mid + 1;
//			else if (cmp > 0)
//				high = mid - 1;
//			else
//				return mid; // key found
//		}
//		return -(low + 1);  // key not found.
//	}
//
//	// Equality Testing
//
//	public static boolean equals(long[] a, long[] a2) {
//		if (a==a2)
//			return true;
//		if (a==null || a2==null)
//			return false;
//
//		int length = a.length;
//		if (a2.length != length)
//			return false;
//
//		for (int i=0; i<length; i++)
//			if (a[i] != a2[i])
//				return false;
//
//		return true;
//	}
//
//	public static boolean equals(int[] a, int[] a2) {
//		if (a==a2)
//			return true;
//		if (a==null || a2==null)
//			return false;
//
//		int length = a.length;
//		if (a2.length != length)
//			return false;
//
//		for (int i=0; i<length; i++)
//			if (a[i] != a2[i])
//				return false;
//
//		return true;
//	}
//
//	public static boolean equals(short[] a, short a2[]) {
//		if (a==a2)
//			return true;
//		if (a==null || a2==null)
//			return false;
//
//		int length = a.length;
//		if (a2.length != length)
//			return false;
//
//		for (int i=0; i<length; i++)
//			if (a[i] != a2[i])
//				return false;
//
//		return true;
//	}
//
//	public static boolean equals(char[] a, char[] a2) {
//		if (a==a2)
//			return true;
//		if (a==null || a2==null)
//			return false;
//
//		int length = a.length;
//		if (a2.length != length)
//			return false;
//
//		for (int i=0; i<length; i++)
//			if (a[i] != a2[i])
//				return false;
//
//		return true;
//	}
//数组的比较算法
//	public static boolean equals(byte[] a, byte[] a2) {
//		if (a==a2)
//			return true;
//		if (a==null || a2==null)
//			return false;
//
//		int length = a.length;
//		if (a2.length != length)
//			return false;
//
//		for (int i=0; i<length; i++)
//			if (a[i] != a2[i])
//				return false;
//
//		return true;
//	}
//
//	public static boolean equals(boolean[] a, boolean[] a2) {
//		if (a==a2)
//			return true;
//		if (a==null || a2==null)
//			return false;
//
//		int length = a.length;
//		if (a2.length != length)
//			return false;
//
//		for (int i=0; i<length; i++)
//			if (a[i] != a2[i])
//				return false;
//
//		return true;
//	}
//
//	public static boolean equals(double[] a, double[] a2) {
//		if (a==a2)
//			return true;
//		if (a==null || a2==null)
//			return false;
//
//		int length = a.length;
//		if (a2.length != length)
//			return false;
//
//		for (int i=0; i<length; i++)
//			if (Double.doubleToLongBits(a[i])!=Double.doubleToLongBits(a2[i]))
//				return false;
//
//		return true;
//	}
//
//	public static boolean equals(float[] a, float[] a2) {
//		if (a==a2)
//			return true;
//		if (a==null || a2==null)
//			return false;
//
//		int length = a.length;
//		if (a2.length != length)
//			return false;
//
//		for (int i=0; i<length; i++)
//			if (Float.floatToIntBits(a[i])!=Float.floatToIntBits(a2[i]))
//				return false;
//
//		return true;
//	}
//
//	public static boolean equals(Object[] a, Object[] a2) {
//		if (a==a2)
//			return true;
//		if (a==null || a2==null)
//			return false;
//
//		int length = a.length;
//		if (a2.length != length)
//			return false;
//
//		for (int i=0; i<length; i++) {
//			Object o1 = a[i];
//			Object o2 = a2[i];
//			if (!(o1==null ? o2==null : o1.equals(o2)))
//				return false;
//		}
//
//		return true;
//	}
//
//	// Filling
//
//	public static void fill(long[] a, long val) {
//		for (int i = 0, len = a.length; i < len; i++)
//			a[i] = val;
//	}
//
//	public static void fill(long[] a, int fromIndex, int toIndex, long val) {
//		rangeCheck(a.length, fromIndex, toIndex);
//		for (int i = fromIndex; i < toIndex; i++)
//			a[i] = val;
//	}
//
//	public static void fill(int[] a, int val) {
//		for (int i = 0, len = a.length; i < len; i++)
//			a[i] = val;
//	}
//
//	public static void fill(int[] a, int fromIndex, int toIndex, int val) {
//		rangeCheck(a.length, fromIndex, toIndex);
//		for (int i = fromIndex; i < toIndex; i++)
//			a[i] = val;
//	}
//
//	public static void fill(short[] a, short val) {
//		for (int i = 0, len = a.length; i < len; i++)
//			a[i] = val;
//	}
//
//	public static void fill(short[] a, int fromIndex, int toIndex, short val) {
//		rangeCheck(a.length, fromIndex, toIndex);
//		for (int i = fromIndex; i < toIndex; i++)
//			a[i] = val;
//	}
//填充算法，Java没有类似C的memset，不然效率更高
//	public static void fill(char[] a, char val) {
//		for (int i = 0, len = a.length; i < len; i++)
//			a[i] = val;
//	}
//
//	public static void fill(char[] a, int fromIndex, int toIndex, char val) {
//		rangeCheck(a.length, fromIndex, toIndex);
//		for (int i = fromIndex; i < toIndex; i++)
//			a[i] = val;
//	}
//
//	public static void fill(byte[] a, byte val) {
//		for (int i = 0, len = a.length; i < len; i++)
//			a[i] = val;
//	}
//
//	public static void fill(byte[] a, int fromIndex, int toIndex, byte val) {
//		rangeCheck(a.length, fromIndex, toIndex);
//		for (int i = fromIndex; i < toIndex; i++)
//			a[i] = val;
//	}
//
//	public static void fill(boolean[] a, boolean val) {
//		for (int i = 0, len = a.length; i < len; i++)
//			a[i] = val;
//	}
//
//	public static void fill(boolean[] a, int fromIndex, int toIndex,
//							boolean val) {
//		rangeCheck(a.length, fromIndex, toIndex);
//		for (int i = fromIndex; i < toIndex; i++)
//			a[i] = val;
//	}
//
//	public static void fill(double[] a, double val) {
//		for (int i = 0, len = a.length; i < len; i++)
//			a[i] = val;
//	}
//
//	public static void fill(double[] a, int fromIndex, int toIndex,double val){
//		rangeCheck(a.length, fromIndex, toIndex);
//		for (int i = fromIndex; i < toIndex; i++)
//			a[i] = val;
//	}
//
//	public static void fill(float[] a, float val) {
//		for (int i = 0, len = a.length; i < len; i++)
//			a[i] = val;
//	}
//
//	public static void fill(float[] a, int fromIndex, int toIndex, float val) {
//		rangeCheck(a.length, fromIndex, toIndex);
//		for (int i = fromIndex; i < toIndex; i++)
//			a[i] = val;
//	}
//
//	public static void fill(Object[] a, Object val) {
//		for (int i = 0, len = a.length; i < len; i++)
//			a[i] = val;
//	}
//
//	public static void fill(Object[] a, int fromIndex, int toIndex, Object val) {
//		rangeCheck(a.length, fromIndex, toIndex);
//		for (int i = fromIndex; i < toIndex; i++)
//			a[i] = val;
//	}
/**
 * 复制：
 * 在对copy方法进行详细介绍之前，必须先来看看java.lang.System类中的arraycopy方法，因为java.util.Arrays类中的copy方法都是基于java.lang.System类的arraycopy方法实现的。
 */
/**
从指定源数组中复制一个数组，复制从指定的位置开始，到目标数组的指定位置结束。从 src 引用的源数组到 dest 引用的目标数组，数组组件的一个子序列被复制下来。被复制的组件的编号等于 length 参数。源数组中位置在 srcPos 到 srcPos+length-1 之间的组件被分别复制到目标数组中的 destPos 到 destPos+length-1 位置。
如果参数 src 和 dest 引用相同的数组对象，则复制的执行过程就好像首先将 srcPos 到 srcPos+length-1 位置的组件复制到一个带有 length 组件的临时数组，然后再将此临时数组的内容复制到目标数组的 destPos 到 destPos+length-1 位置一样。

如果 dest 为 null，则抛出 NullPointerException 异常。
如果 src 为 null, 则抛出 NullPointerException 异常，并且不会修改目标数组。
否则，只要下列任何情况为真，则抛出 ArrayStoreException 异常并且不会修改目标数组：
　　src 参数指的是非数组对象。
　　dest 参数指的是非数组对象。
　　src 参数和 dest 参数指的是那些其组件类型为不同基本类型的数组。
　　src 参数指的是具有基本组件类型的数组且 dest 参数指的是具有引用组件类型的数组。
　　src 参数指的是具有引用组件类型的数组且 dest 参数指的是具有基本组件类型的数组。
否则，只要下列任何情况为真，则抛出 IndexOutOfBoundsException 异常，并且不会修改目标数组：
　　srcPos 参数为负。
　　destPos 参数为负。
　　length 参数为负。
　　srcPos+length 大于 src.length，即源数组的长度。
　　destPos+length 大于 dest.length，即目标数组的长度。
否则，如果源数组中 srcPos 到 srcPos+length-1 位置上的实际组件通过分配转换并不能转换成目标数组的组件类型，则抛出 ArrayStoreException 异常。在这种情况下，将 k 设置为比长度小的最小非负整数，这样就无法将 src[srcPos+k] 转换为目标数组的组件类型；当抛出异常时，从 srcPos 到 srcPos+k-1 位置上的源数组组件已经被复制到目标数组中的 destPos 到 destPos+k-1 位置，而目标数组中的其他位置不会被修改。

 public static native void arraycopy(Object src,int srcPos,Object dest,int destPos,int length);
 */
//复制指定的数组（针对T类型）
//	@SuppressWarnings("unchecked")
//	public static <T> T[] copyOf(T[] original, int newLength) {
//		return (T[]) copyOf(original, newLength, original.getClass());
//	}
//复制指定的数组（针对T类型）
//	public static <T,U> T[] copyOf(U[] original, int newLength, Class<? extends T[]> newType) {
//		@SuppressWarnings("unchecked")
//		T[] copy = ((Object)newType == (Object)Object[].class)
//				? (T[]) new Object[newLength]
//				: (T[]) Array.newInstance(newType.getComponentType(), newLength);
//		System.arraycopy(original, 0, copy, 0,
//				Math.min(original.length, newLength));
//		return copy;
//	}
//复制指定的数组（针对byte类型）
//	public static byte[] copyOf(byte[] original, int newLength) {
//		byte[] copy = new byte[newLength];
//		System.arraycopy(original, 0, copy, 0,
//				Math.min(original.length, newLength));
//		return copy;
//	}
//复制指定的数组（针对short类型）
//	public static short[] copyOf(short[] original, int newLength) {
//		short[] copy = new short[newLength];
//		System.arraycopy(original, 0, copy, 0,
//				Math.min(original.length, newLength));
//		return copy;
//	}
//复制指定的数组（针对int类型）
//	public static int[] copyOf(int[] original, int newLength) {
//		int[] copy = new int[newLength];
//		System.arraycopy(original, 0, copy, 0,
//				Math.min(original.length, newLength));
//		return copy;
//	}
//复制指定的数组（针对long类型）
//	public static long[] copyOf(long[] original, int newLength) {
//		long[] copy = new long[newLength];
//		System.arraycopy(original, 0, copy, 0,
//				Math.min(original.length, newLength));
//		return copy;
//	}
//复制指定的数组（针对char类型）
//	public static char[] copyOf(char[] original, int newLength) {
//		char[] copy = new char[newLength];
//		System.arraycopy(original, 0, copy, 0,
//				Math.min(original.length, newLength));
//		return copy;
//	}
//复制指定的数组（针对float类型）
//	public static float[] copyOf(float[] original, int newLength) {
//		float[] copy = new float[newLength];
//		System.arraycopy(original, 0, copy, 0,
//				Math.min(original.length, newLength));
//		return copy;
//	}
//复制指定的数组（针对double类型）
//	public static double[] copyOf(double[] original, int newLength) {
//		double[] copy = new double[newLength];
//		System.arraycopy(original, 0, copy, 0,
//				Math.min(original.length, newLength));
//		return copy;
//	}
//复制指定的数组（针对boolean类型）
//	public static boolean[] copyOf(boolean[] original, int newLength) {
//		boolean[] copy = new boolean[newLength];
//		System.arraycopy(original, 0, copy, 0,
//				Math.min(original.length, newLength));
//		return copy;
//	}
//
//	@SuppressWarnings("unchecked")
//	public static <T> T[] copyOfRange(T[] original, int from, int to) {
//		return copyOfRange(original, from, to, (Class<? extends T[]>) original.getClass());
//	}
//
//	public static <T,U> T[] copyOfRange(U[] original, int from, int to, Class<? extends T[]> newType) {
//		int newLength = to - from;
//		if (newLength < 0)
//			throw new IllegalArgumentException(from + " > " + to);
//		@SuppressWarnings("unchecked")
//		T[] copy = ((Object)newType == (Object)Object[].class)
//				? (T[]) new Object[newLength]
//				: (T[]) Array.newInstance(newType.getComponentType(), newLength);
//		System.arraycopy(original, from, copy, 0,
//				Math.min(original.length - from, newLength));
//		return copy;
//	}
//
//	public static byte[] copyOfRange(byte[] original, int from, int to) {
//		int newLength = to - from;
//		if (newLength < 0)
//			throw new IllegalArgumentException(from + " > " + to);
//		byte[] copy = new byte[newLength];
//		System.arraycopy(original, from, copy, 0,
//				Math.min(original.length - from, newLength));
//		return copy;
//	}
//
//	public static short[] copyOfRange(short[] original, int from, int to) {
//		int newLength = to - from;
//		if (newLength < 0)
//			throw new IllegalArgumentException(from + " > " + to);
//		short[] copy = new short[newLength];
//		System.arraycopy(original, from, copy, 0,
//				Math.min(original.length - from, newLength));
//		return copy;
//	}
//
//	public static int[] copyOfRange(int[] original, int from, int to) {
//		int newLength = to - from;
//		if (newLength < 0)
//			throw new IllegalArgumentException(from + " > " + to);
//		int[] copy = new int[newLength];
//		System.arraycopy(original, from, copy, 0,
//				Math.min(original.length - from, newLength));
//		return copy;
//	}
//
//	public static long[] copyOfRange(long[] original, int from, int to) {
//		int newLength = to - from;
//		if (newLength < 0)
//			throw new IllegalArgumentException(from + " > " + to);
//		long[] copy = new long[newLength];
//		System.arraycopy(original, from, copy, 0,
//				Math.min(original.length - from, newLength));
//		return copy;
//	}
//
//	public static char[] copyOfRange(char[] original, int from, int to) {
//		int newLength = to - from;
//		if (newLength < 0)
//			throw new IllegalArgumentException(from + " > " + to);
//		char[] copy = new char[newLength];
//		System.arraycopy(original, from, copy, 0,
//				Math.min(original.length - from, newLength));
//		return copy;
//	}
//
//	public static float[] copyOfRange(float[] original, int from, int to) {
//		int newLength = to - from;
//		if (newLength < 0)
//			throw new IllegalArgumentException(from + " > " + to);
//		float[] copy = new float[newLength];
//		System.arraycopy(original, from, copy, 0,
//				Math.min(original.length - from, newLength));
//		return copy;
//	}
//
//	public static double[] copyOfRange(double[] original, int from, int to) {
//		int newLength = to - from;
//		if (newLength < 0)
//			throw new IllegalArgumentException(from + " > " + to);
//		double[] copy = new double[newLength];
//		System.arraycopy(original, from, copy, 0,
//				Math.min(original.length - from, newLength));
//		return copy;
//	}
//
//	public static boolean[] copyOfRange(boolean[] original, int from, int to) {
//		int newLength = to - from;
//		if (newLength < 0)
//			throw new IllegalArgumentException(from + " > " + to);
//		boolean[] copy = new boolean[newLength];
//		System.arraycopy(original, from, copy, 0,
//				Math.min(original.length - from, newLength));
//		return copy;
//	}
//
//	@SafeVarargs
//	@SuppressWarnings("varargs")
//	public static <T> List<T> asList(T... a) {
//		return new ArrayList<>(a);
//	}
//
//	private static class ArrayList<E> extends AbstractList<E>
//			implements RandomAccess, java.io.Serializable
//	{
//		private static final long serialVersionUID = -2764017481108945198L;
//		private final E[] a;
//
//		ArrayList(E[] array) {
//			a = Objects.requireNonNull(array);
//		}
//
//		@Override
//		public int size() {
//			return a.length;
//		}
//
//		@Override
//		public Object[] toArray() {
//			return a.clone();
//		}
//
//		@Override
//		@SuppressWarnings("unchecked")
//		public <T> T[] toArray(T[] a) {
//			int size = size();
//			if (a.length < size)
//				return Arrays.copyOf(this.a, size,
//						(Class<? extends T[]>) a.getClass());
//			System.arraycopy(this.a, 0, a, 0, size);
//			if (a.length > size)
//				a[size] = null;
//			return a;
//		}
//
//		@Override
//		public E get(int index) {
//			return a[index];
//		}
//
//		@Override
//		public E set(int index, E element) {
//			E oldValue = a[index];
//			a[index] = element;
//			return oldValue;
//		}
//
//		@Override
//		public int indexOf(Object o) {
//			E[] a = this.a;
//			if (o == null) {
//				for (int i = 0; i < a.length; i++)
//					if (a[i] == null)
//						return i;
//			} else {
//				for (int i = 0; i < a.length; i++)
//					if (o.equals(a[i]))
//						return i;
//			}
//			return -1;
//		}
//
//		@Override
//		public boolean contains(Object o) {
//			return indexOf(o) != -1;
//		}
//
//		@Override
//		public Spliterator<E> spliterator() {
//			return Spliterators.spliterator(a, Spliterator.ORDERED);
//		}
//
//		@Override
//		public void forEach(Consumer<? super E> action) {
//			Objects.requireNonNull(action);
//			for (E e : a) {
//				action.accept(e);
//			}
//		}
//
//		@Override
//		public void replaceAll(UnaryOperator<E> operator) {
//			Objects.requireNonNull(operator);
//			E[] a = this.a;
//			for (int i = 0; i < a.length; i++) {
//				a[i] = operator.apply(a[i]);
//			}
//		}
//
//		@Override
//		public void sort(Comparator<? super E> c) {
//			Arrays.sort(a, c);
//		}
//	}
//
//	public static int hashCode(long a[]) {
//		if (a == null)
//			return 0;
//
//		int result = 1;
//		for (long element : a) {
//			int elementHash = (int)(element ^ (element >>> 32));
//			result = 31 * result + elementHash;
//		}
//
//		return result;
//	}
//
//	public static int hashCode(int a[]) {
//		if (a == null)
//			return 0;
//
//		int result = 1;
//		for (int element : a)
//			result = 31 * result + element;
//
//		return result;
//	}
//
//	public static int hashCode(short a[]) {
//		if (a == null)
//			return 0;
//
//		int result = 1;
//		for (short element : a)
//			result = 31 * result + element;
//
//		return result;
//	}
//
//	public static int hashCode(char a[]) {
//		if (a == null)
//			return 0;
//
//		int result = 1;
//		for (char element : a)
//			result = 31 * result + element;
//
//		return result;
//	}
//计算Hash值的算法
//	public static int hashCode(byte a[]) {
//		if (a == null)
//			return 0;
//
//		int result = 1;
//		for (byte element : a)
//			result = 31 * result + element;
//
//		return result;
//	}
//
//	public static int hashCode(boolean a[]) {
//		if (a == null)
//			return 0;
//
//		int result = 1;
//		for (boolean element : a)
//			result = 31 * result + (element ? 1231 : 1237);
//
//		return result;
//	}
//
//	public static int hashCode(float a[]) {
//		if (a == null)
//			return 0;
//
//		int result = 1;
//		for (float element : a)
//			result = 31 * result + Float.floatToIntBits(element);
//
//		return result;
//	}
//
//	public static int hashCode(double a[]) {
//		if (a == null)
//			return 0;
//
//		int result = 1;
//		for (double element : a) {
//			long bits = Double.doubleToLongBits(element);
//			result = 31 * result + (int)(bits ^ (bits >>> 32));
//		}
//		return result;
//	}
//
//	public static int hashCode(Object a[]) {
//		if (a == null)
//			return 0;
//
//		int result = 1;
//
//		for (Object element : a)
//			result = 31 * result + (element == null ? 0 : element.hashCode());
//
//		return result;
//	}
//
//	public static int deepHashCode(Object a[]) {
//		if (a == null)
//			return 0;
//
//		int result = 1;
//
//		for (Object element : a) {
//			int elementHash = 0;
//			if (element instanceof Object[])
//				elementHash = deepHashCode((Object[]) element);
//			else if (element instanceof byte[])
//				elementHash = hashCode((byte[]) element);
//			else if (element instanceof short[])
//				elementHash = hashCode((short[]) element);
//			else if (element instanceof int[])
//				elementHash = hashCode((int[]) element);
//			else if (element instanceof long[])
//				elementHash = hashCode((long[]) element);
//			else if (element instanceof char[])
//				elementHash = hashCode((char[]) element);
//			else if (element instanceof float[])
//				elementHash = hashCode((float[]) element);
//			else if (element instanceof double[])
//				elementHash = hashCode((double[]) element);
//			else if (element instanceof boolean[])
//				elementHash = hashCode((boolean[]) element);
//			else if (element != null)
//				elementHash = element.hashCode();
//
//			result = 31 * result + elementHash;
//		}
//
//		return result;
//	}
//
//	public static boolean deepEquals(Object[] a1, Object[] a2) {
//		if (a1 == a2)
//			return true;
//		if (a1 == null || a2==null)
//			return false;
//		int length = a1.length;
//		if (a2.length != length)
//			return false;
//
//		for (int i = 0; i < length; i++) {
//			Object e1 = a1[i];
//			Object e2 = a2[i];
//
//			if (e1 == e2)
//				continue;
//			if (e1 == null)
//				return false;
//
//			// Figure out whether the two elements are equal
//			boolean eq = deepEquals0(e1, e2);
//
//			if (!eq)
//				return false;
//		}
//		return true;
//	}
//
//	static boolean deepEquals0(Object e1, Object e2) {
//		assert e1 != null;
//		boolean eq;
//		if (e1 instanceof Object[] && e2 instanceof Object[])
//			eq = deepEquals ((Object[]) e1, (Object[]) e2);
//		else if (e1 instanceof byte[] && e2 instanceof byte[])
//			eq = equals((byte[]) e1, (byte[]) e2);
//		else if (e1 instanceof short[] && e2 instanceof short[])
//			eq = equals((short[]) e1, (short[]) e2);
//		else if (e1 instanceof int[] && e2 instanceof int[])
//			eq = equals((int[]) e1, (int[]) e2);
//		else if (e1 instanceof long[] && e2 instanceof long[])
//			eq = equals((long[]) e1, (long[]) e2);
//		else if (e1 instanceof char[] && e2 instanceof char[])
//			eq = equals((char[]) e1, (char[]) e2);
//		else if (e1 instanceof float[] && e2 instanceof float[])
//			eq = equals((float[]) e1, (float[]) e2);
//		else if (e1 instanceof double[] && e2 instanceof double[])
//			eq = equals((double[]) e1, (double[]) e2);
//		else if (e1 instanceof boolean[] && e2 instanceof boolean[])
//			eq = equals((boolean[]) e1, (boolean[]) e2);
//		else
//			eq = e1.equals(e2);
//		return eq;
//	}
//
//	public static String toString(long[] a) {
//		if (a == null)
//			return "null";
//		int iMax = a.length - 1;
//		if (iMax == -1)
//			return "[]";
//
//		StringBuilder b = new StringBuilder();
//		b.append('[');
//		for (int i = 0; ; i++) {
//			b.append(a[i]);
//			if (i == iMax)
//				return b.append(']').toString();
//			b.append(", ");
//		}
//	}
//
//	public static String toString(int[] a) {
//		if (a == null)
//			return "null";
//		int iMax = a.length - 1;
//		if (iMax == -1)
//			return "[]";
//
//		StringBuilder b = new StringBuilder();
//		b.append('[');
//		for (int i = 0; ; i++) {
//			b.append(a[i]);
//			if (i == iMax)
//				return b.append(']').toString();
//			b.append(", ");
//		}
//	}
//
//	public static String toString(short[] a) {
//		if (a == null)
//			return "null";
//		int iMax = a.length - 1;
//		if (iMax == -1)
//			return "[]";
//
//		StringBuilder b = new StringBuilder();
//		b.append('[');
//		for (int i = 0; ; i++) {
//			b.append(a[i]);
//			if (i == iMax)
//				return b.append(']').toString();
//			b.append(", ");
//		}
//	}
//
//	public static String toString(char[] a) {
//		if (a == null)
//			return "null";
//		int iMax = a.length - 1;
//		if (iMax == -1)
//			return "[]";
//
//		StringBuilder b = new StringBuilder();
//		b.append('[');
//		for (int i = 0; ; i++) {
//			b.append(a[i]);
//			if (i == iMax)
//				return b.append(']').toString();
//			b.append(", ");
//		}
//	}
//
//	public static String toString(byte[] a) {
//		if (a == null)
//			return "null";
//		int iMax = a.length - 1;
//		if (iMax == -1)
//			return "[]";
//
//		StringBuilder b = new StringBuilder();
//		b.append('[');
//		for (int i = 0; ; i++) {
//			b.append(a[i]);
//			if (i == iMax)
//				return b.append(']').toString();
//			b.append(", ");
//		}
//	}
//
//	public static String toString(boolean[] a) {
//		if (a == null)
//			return "null";
//		int iMax = a.length - 1;
//		if (iMax == -1)
//			return "[]";
//
//		StringBuilder b = new StringBuilder();
//		b.append('[');
//		for (int i = 0; ; i++) {
//			b.append(a[i]);
//			if (i == iMax)
//				return b.append(']').toString();
//			b.append(", ");
//		}
//	}
//
//	public static String toString(float[] a) {
//		if (a == null)
//			return "null";
//
//		int iMax = a.length - 1;
//		if (iMax == -1)
//			return "[]";
//
//		StringBuilder b = new StringBuilder();
//		b.append('[');
//		for (int i = 0; ; i++) {
//			b.append(a[i]);
//			if (i == iMax)
//				return b.append(']').toString();
//			b.append(", ");
//		}
//	}
//
//	public static String toString(double[] a) {
//		if (a == null)
//			return "null";
//		int iMax = a.length - 1;
//		if (iMax == -1)
//			return "[]";
//
//		StringBuilder b = new StringBuilder();
//		b.append('[');
//		for (int i = 0; ; i++) {
//			b.append(a[i]);
//			if (i == iMax)
//				return b.append(']').toString();
//			b.append(", ");
//		}
//	}
//
//	public static String toString(Object[] a) {
//		if (a == null)
//			return "null";
//
//		int iMax = a.length - 1;
//		if (iMax == -1)
//			return "[]";
//
//		StringBuilder b = new StringBuilder();
//		b.append('[');
//		for (int i = 0; ; i++) {
//			b.append(String.valueOf(a[i]));
//			if (i == iMax)
//				return b.append(']').toString();
//			b.append(", ");
//		}
//	}
//
//	public static String deepToString(Object[] a) {
//		if (a == null)
//			return "null";
//
//		int bufLen = 20 * a.length;
//		if (a.length != 0 && bufLen <= 0)
//			bufLen = Integer.MAX_VALUE;
//		StringBuilder buf = new StringBuilder(bufLen);
//		deepToString(a, buf, new HashSet<Object[]>());
//		return buf.toString();
//	}
//
//	private static void deepToString(Object[] a, StringBuilder buf,
//									 Set<Object[]> dejaVu) {
//		if (a == null) {
//			buf.append("null");
//			return;
//		}
//		int iMax = a.length - 1;
//		if (iMax == -1) {
//			buf.append("[]");
//			return;
//		}
//
//		dejaVu.add(a);
//		buf.append('[');
//		for (int i = 0; ; i++) {
//
//			Object element = a[i];
//			if (element == null) {
//				buf.append("null");
//			} else {
//				Class<?> eClass = element.getClass();
//
//				if (eClass.isArray()) {
//					if (eClass == byte[].class)
//						buf.append(toString((byte[]) element));
//					else if (eClass == short[].class)
//						buf.append(toString((short[]) element));
//					else if (eClass == int[].class)
//						buf.append(toString((int[]) element));
//					else if (eClass == long[].class)
//						buf.append(toString((long[]) element));
//					else if (eClass == char[].class)
//						buf.append(toString((char[]) element));
//					else if (eClass == float[].class)
//						buf.append(toString((float[]) element));
//					else if (eClass == double[].class)
//						buf.append(toString((double[]) element));
//					else if (eClass == boolean[].class)
//						buf.append(toString((boolean[]) element));
//					else { // element is an array of object references
//						if (dejaVu.contains(element))
//							buf.append("[...]");
//						else
//							deepToString((Object[])element, buf, dejaVu);
//					}
//				} else {  // element is non-null and not an array
//					buf.append(element.toString());
//				}
//			}
//			if (i == iMax)
//				break;
//			buf.append(", ");
//		}
//		buf.append(']');
//		dejaVu.remove(a);
//	}
//
//
//	public static <T> void setAll(T[] array, IntFunction<? extends T> generator) {
//		Objects.requireNonNull(generator);
//		for (int i = 0; i < array.length; i++)
//			array[i] = generator.apply(i);
//	}
//
//	public static <T> void parallelSetAll(T[] array, IntFunction<? extends T> generator) {
//		Objects.requireNonNull(generator);
//		IntStream.range(0, array.length).parallel().forEach(i -> { array[i] = generator.apply(i); });
//	}
//
//	public static void setAll(int[] array, IntUnaryOperator generator) {
//		Objects.requireNonNull(generator);
//		for (int i = 0; i < array.length; i++)
//			array[i] = generator.applyAsInt(i);
//	}
//
//	public static void parallelSetAll(int[] array, IntUnaryOperator generator) {
//		Objects.requireNonNull(generator);
//		IntStream.range(0, array.length).parallel().forEach(i -> { array[i] = generator.applyAsInt(i); });
//	}
//
//	public static void setAll(long[] array, IntToLongFunction generator) {
//		Objects.requireNonNull(generator);
//		for (int i = 0; i < array.length; i++)
//			array[i] = generator.applyAsLong(i);
//	}
//
//	public static void parallelSetAll(long[] array, IntToLongFunction generator) {
//		Objects.requireNonNull(generator);
//		IntStream.range(0, array.length).parallel().forEach(i -> { array[i] = generator.applyAsLong(i); });
//	}
//
//	public static void setAll(double[] array, IntToDoubleFunction generator) {
//		Objects.requireNonNull(generator);
//		for (int i = 0; i < array.length; i++)
//			array[i] = generator.applyAsDouble(i);
//	}
//
//	public static void parallelSetAll(double[] array, IntToDoubleFunction generator) {
//		Objects.requireNonNull(generator);
//		IntStream.range(0, array.length).parallel().forEach(i -> { array[i] = generator.applyAsDouble(i); });
//	}
//
//	public static <T> Spliterator<T> spliterator(T[] array) {
//		return Spliterators.spliterator(array,
//				Spliterator.ORDERED | Spliterator.IMMUTABLE);
//	}
//
//	public static <T> Spliterator<T> spliterator(T[] array, int startInclusive, int endExclusive) {
//		return Spliterators.spliterator(array, startInclusive, endExclusive,
//				Spliterator.ORDERED | Spliterator.IMMUTABLE);
//	}
//
//	public static Spliterator.OfInt spliterator(int[] array) {
//		return Spliterators.spliterator(array,
//				Spliterator.ORDERED | Spliterator.IMMUTABLE);
//	}
//	public static Spliterator.OfInt spliterator(int[] array, int startInclusive, int endExclusive) {
//		return Spliterators.spliterator(array, startInclusive, endExclusive,
//				Spliterator.ORDERED | Spliterator.IMMUTABLE);
//	}
//
//	public static Spliterator.OfLong spliterator(long[] array) {
//		return Spliterators.spliterator(array,
//				Spliterator.ORDERED | Spliterator.IMMUTABLE);
//	}
//
//	public static Spliterator.OfLong spliterator(long[] array, int startInclusive, int endExclusive) {
//		return Spliterators.spliterator(array, startInclusive, endExclusive,
//				Spliterator.ORDERED | Spliterator.IMMUTABLE);
//	}
//
//	public static Spliterator.OfDouble spliterator(double[] array) {
//		return Spliterators.spliterator(array,
//				Spliterator.ORDERED | Spliterator.IMMUTABLE);
//	}
//
//	public static Spliterator.OfDouble spliterator(double[] array, int startInclusive, int endExclusive) {
//		return Spliterators.spliterator(array, startInclusive, endExclusive,
//				Spliterator.ORDERED | Spliterator.IMMUTABLE);
//	}
//
//	public static <T> Stream<T> stream(T[] array) {
//		return stream(array, 0, array.length);
//	}
//
//	public static <T> Stream<T> stream(T[] array, int startInclusive, int endExclusive) {
//		return StreamSupport.stream(spliterator(array, startInclusive, endExclusive), false);
//	}
//
//	public static IntStream stream(int[] array) {
//		return stream(array, 0, array.length);
//	}
//
//	public static IntStream stream(int[] array, int startInclusive, int endExclusive) {
//		return StreamSupport.intStream(spliterator(array, startInclusive, endExclusive), false);
//	}
//
//	public static LongStream stream(long[] array) {
//		return stream(array, 0, array.length);
//	}
//
//	public static LongStream stream(long[] array, int startInclusive, int endExclusive) {
//		return StreamSupport.longStream(spliterator(array, startInclusive, endExclusive), false);
//	}
//
//	public static DoubleStream stream(double[] array) {
//		return stream(array, 0, array.length);
//	}
//
//	public static DoubleStream stream(double[] array, int startInclusive, int endExclusive) {
//		return StreamSupport.doubleStream(spliterator(array, startInclusive, endExclusive), false);
//	}
//}



