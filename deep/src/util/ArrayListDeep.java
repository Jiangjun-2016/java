package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * java list 集合学习
 *
 * @author fxf
 * @create 2017-09-12 10:23
 **/

public class ArrayListDeep {


	public static void main(String[] args) {
		// 创建一个空的数组链表对象list，list用来存放String类型的数据
		ArrayList<String> list = new ArrayList<String>();

		// 增加元素到list对象中
		list.add("Item1");
		list.add("Item2");
		list.add(2, "Item3"); // 此条语句将会把“Item3”字符串增加到list的第3个位置。
		list.add("Item4");

		// 显示数组链表中的内容
		System.out.println("The arraylist contains the following elements: "
				+ list);

		// 检查元素的位置
		int pos = list.indexOf("Item2");
		System.out.println("The index of Item2 is: " + pos);

		// 检查数组链表是否为空
		boolean check = list.isEmpty();
		System.out.println("Checking if the arraylist is empty: " + check);

		// 获取链表的大小
		int size = list.size();
		System.out.println("The size of the list is: " + size);

		// 检查数组链表中是否包含某元素
		boolean element = list.contains("Item5");
		System.out
				.println("Checking if the arraylist contains the object Item5: "
						+ element);

		// 获取指定位置上的元素
		String item = list.get(0);
		System.out.println("The item is the index 0 is: " + item);

		// 遍历arraylist中的元素

		// 第1种方法: 循环使用元素的索引和链表的大小
		System.out
				.println("Retrieving items with loop using index and size list");
		for (int i = 0; i < list.size(); i++) {
			System.out.println("Index: " + i + " - Item: " + list.get(i));
		}

		// 第2种方法:使用foreach循环
		System.out.println("Retrieving items using foreach loop");
		for (String str : list) {
			System.out.println("Item is: " + str);
		}

		// 第三种方法:使用迭代器
		// hasNext(): 返回true表示链表链表中还有元素
		// next(): 返回下一个元素
		System.out.println("Retrieving items using iterator");
		for (Iterator<String> it = list.iterator(); it.hasNext();) {
			System.out.println("Item is: " + it.next());
		}

		// 替换元素
		list.set(1, "NewItem");
		System.out.println("The arraylist after the replacement is: " + list);

		// 移除元素
		// 移除第0个位置上的元素
		list.remove(0);

		// 移除第一次找到的 "Item3"元素
		list.remove("Item3");

		System.out.println("The final contents of the arraylist are: " + list);

		// 转换 ArrayList 为 Array
		String[] simpleArray = list.toArray(new String[list.size()]);
		System.out.println("The array created after the conversion of our arraylist is: "
				+ Arrays.toString(simpleArray));

	}

}

//ArrayList源码
/**
 *  ArrayList<E>支持泛型 继承自AbstractList，实现了List、RandomAccess、Cloneable、java.io.Serializable接口。
 *  1 实现RandomAccess接口，实现使用的标记接口，用来表明支持快速(通常是固定时间)随机访问。这个接口的主要目的是允许一般的算法更改它们的行为，
 *  从而在随机或连续访问列表时提供更好的性能。
 *  2 实现了Cloneable接口的类，可以调用Object.clone方法返回该对象的浅拷贝。
 *  3 通过实现 java.io.Serializable 接口以启用其序列化功能。
 *
 */
// public class ArrayList<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, java.io.Serializable{
/** 序列化ID*/
// private static final long serialVersionUID = 8683452581122892189L;
/** 默认初始容量 10 */
// private static final int DEFAULT_CAPACITY = 10;
/** 用于空实例共享空数组实例 */
//	private static final Object[] EMPTY_ELEMENTDATA = {};
/** 默认的空数组*/
//	private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
/**
 *Java的serialization提供了一种持久化对象实例的机制。当持久化对象时，可能有一个特殊的对象数据成员，我们不想用serialization机制来保存它。
 * 为了在一个特定对象的一个域上关闭serialization，可以在这个域前加上关键字transient。
 *transient是Java语言的关键字，用来表示一个域不是该对象串行化的一部分。
 *被标记为transient的属性在对象被序列化的时候不会被保存,这个字段的生命周期仅存于调用者的内存中而不会写到磁盘里持久化。
 */
/** 存储ArrayList内的元素
 * 类的属性中核心的属性为elementData，类型为Object[]，用于存放实际元素，
 * 并且被标记为transient，也就意味着在序列化的时候，此字段是不会被序列化的。
 */
//	transient Object[] elementData;

/**  实际元素大小，默认为0 实际元素大小，默认为0*/
//	private int size;

/**
 * 1 ArrayList(int)型构造函数
 */
//	public ArrayList(int initialCapacity) {
//		if (initialCapacity > 0) { // 初始容量大于0
//			this.elementData = new Object[initialCapacity]; // 初始化元素数组
//		} else if (initialCapacity == 0) {  // 初始容量为0
//			this.elementData = EMPTY_ELEMENTDATA; // 为空对象数组
//		} else { // 初始容量小于0，抛出异常
//			throw new IllegalArgumentException("Illegal Capacity: "+
//					initialCapacity);
//		}
//	}
/**
 * 2 ArrayList()型构造函数
 */
//	public ArrayList() {
//		this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA; // 无参构造函数，设置元素数组为空
//	}
/**
 * 3 ArrayList(Collection<? extends E>)型构造函数　　
 * 	说明：当传递的参数为集合类型时，会把集合类型转化为数组类型，并赋值给elementData。
 */
//	public ArrayList(Collection<? extends E> c) { // 集合参数构造函数
//		elementData = c.toArray(); // 转化为数组
//		if ((size = elementData.length) != 0) { // 参数为非空集合
//			if (elementData.getClass() != Object[].class) // 是否成功转化为Object类型数组
//				elementData = Arrays.copyOf(elementData, size, Object[].class); // 不为Object数组的话就进行复制
//		} else {// 集合大小为空，则设置元素数组为空
//			this.elementData = EMPTY_ELEMENTDATA;
//		}
//	}
/**
 * 因为容量常常会大于实际元素的数量。内存紧张时，可以调用该方法删除预留的位置，调整容量为元素实际数量。
 * 如果确定不会再有元素添加进来时也可以调用该方法来节约空间
 * */
//	public void trimToSize() {
//		modCount++;
//		if (size < elementData.length) {
//			elementData = (size == 0)
//					? EMPTY_ELEMENTDATA
//					: Arrays.copyOf(elementData, size);
//		}
//	}
/** 使用指定参数设置数组容量*/
//	public void ensureCapacity(int minCapacity) {
//		int minExpand = (elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA) //如果数组为空，容量预取0，否则去默认值(10)
//				? 0
//				: DEFAULT_CAPACITY;
//		if (minCapacity > minExpand) { //若参数大于预设的容量，在使用该参数进一步设置数组容量
//			ensureExplicitCapacity(minCapacity);
//		}
//	}
/** 确保elementData数组有合适的大小 用于添加元素时，确保数组容量*/
//	private void ensureCapacityInternal(int minCapacity) {
//		if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {// 判断元素数组是否为空数组
//			minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);// 取较大值 根默认值10比较，
//		}
//		ensureExplicitCapacity(minCapacity);
//	}
/** 也是为了确保elemenData数组有合适的大小  如果参数大于数组容量，就增加数组容量*/
//	private void ensureExplicitCapacity(int minCapacity) {
//		modCount++; // 结构性修改加1
//		if (minCapacity - elementData.length > 0)
//			grow(minCapacity);
//	}
/** 数组的最大容量，可能会导致内存溢出(VM内存限制)*/
//	private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

/** grow函数才会对数组进行扩容，ensureCapacityInternal、ensureExplicitCapacity都只是过程，最后完成实际扩容操作还是得看grow函数
 * 增加容量，以确保它可以至少持有由参数指定的元素的数目
 * */
//	private void grow(int minCapacity) {
//		int oldCapacity = elementData.length; // 旧容量
//		int newCapacity = oldCapacity + (oldCapacity >> 1); // 新容量为旧容量的1.5倍
//		if (newCapacity - minCapacity < 0) // 新容量小于参数指定容量，修改新容量
//			newCapacity = minCapacity;
//		if (newCapacity - MAX_ARRAY_SIZE > 0) // 新容量大于最大容量
//			newCapacity = hugeCapacity(minCapacity); // 指定新容量
//		elementData = Arrays.copyOf(elementData, newCapacity); // 拷贝扩容
//	}
/** 检查是否溢出，若没有溢出，返回最大整数值(java中的int为4字节，所以最大为0x7fffffff)或默认最大值*/
//	private static int hugeCapacity(int minCapacity) {
//		if (minCapacity < 0)
//			throw new OutOfMemoryError();
//		return (minCapacity > MAX_ARRAY_SIZE) ?
//				Integer.MAX_VALUE :
//				MAX_ARRAY_SIZE;
//	}
/** 返回数组大小*/
//	public int size() {
//		return size;
//	}
/** 是否为空*/
//	public boolean isEmpty() {
//		return size == 0;
//	}
/** 是否包含一个数*/
//	public boolean contains(Object o) {
//		return indexOf(o) >= 0;
//	}
/** 从首开始查找数组里面是否存在指定元素
 *  说明：从头开始查找与指定元素相等的元素，注意，是可以查找null元素的，意味着ArrayList中可以存放null元素的。与此函数对应的lastIndexOf，表示从尾部开始查找。
 *  返回一个值在数组首次出现的位置，会根据是否为null使用不同方式判断。不存在就返回-1。时间复杂度为O(N)
 * */
//	public int indexOf(Object o) {
//		if (o == null) { // 查找的元素为空
//			for (int i = 0; i < size; i++) // 遍历数组，找到第一个为空的元素，返回下标
//				if (elementData[i]==null)
//					return i;
//		} else { // 查找的元素不为空
//			for (int i = 0; i < size; i++) // 遍历数组，找到第一个和指定元素相等的元素，返回下标
//				if (o.equals(elementData[i]))
//					return i;
//		}
//		return -1;// 没有找到，返回空
//	}
/** 从尾部开始查找数组里面是否存在指定元素
 *  返回一个值在数组最后一次出现的位置，不存在就返回-1。时间复杂度为O(N)
 * */
//	public int lastIndexOf(Object o) {
//		if (o == null) {
//			for (int i = size-1; i >= 0; i--)
//				if (elementData[i]==null)
//					return i;
//		} else {
//			for (int i = size-1; i >= 0; i--)
//				if (o.equals(elementData[i]))
//					return i;
//		}
//		return -1;
//	}
/** 返回副本，元素本身没有被复制，复制过程数组发生改变会抛出异常*/
//	public Object clone() {
//		try {
//			ArrayList<?> v = (ArrayList<?>) super.clone();
//			v.elementData = Arrays.copyOf(elementData, size);
//			v.modCount = 0;
//			return v;
//		} catch (CloneNotSupportedException e) {
//			throw new InternalError(e);
//		}
//	}
/** 转换为Object数组，使用Arrays.copyOf()方法*/
//	public Object[] toArray() {
//		return Arrays.copyOf(elementData, size);
//	}
/**
 * 返回一个数组，使用运行时确定类型，该数组包含在这个列表中的所有元素（从第一到最后一个元素）
 * 返回的数组容量由参数和本数组中较大值确定
 */
//	@SuppressWarnings("unchecked")
//	public <T> T[] toArray(T[] a) {
//		if (a.length < size)
//			return (T[]) Arrays.copyOf(elementData, size, a.getClass());
//		System.arraycopy(elementData, 0, a, 0, size);
//		if (a.length > size)
//			a[size] = null;
//		return a;
//	}
/** 说明：返回的值都经过了向下转型（Object -> E）
 *  返回指定位置的值，因为是数组，所以速度特别快
 * */
//	@SuppressWarnings("unchecked")
//	E elementData(int index) {
//		return (E) elementData[index];
//	}
/** 说明：get函数会检查索引值是否合法（只检查是否大于size，而没有检查是否小于0）
 *  返回指定位置的值，但是会检查这个位置数否超出数组长度
 * */
//	public E get(int index) {
//		rangeCheck(index); // 检验索引是否合法
//		return elementData(index);
//	}
/** 说明：设定指定下标索引的元素值
 *  设置指定位置为一个新值，并返回之前的值，会检查这个位置是否超出数组长度
 * */
//	public E set(int index, E element) {
//		rangeCheck(index); // 检验索引是否合法
//		E oldValue = elementData(index); // 旧值
//		elementData[index] = element; // 赋新值
//		return oldValue; // 返回旧值
//	}
//
/**
 * 说明：正常情况下会扩容1.5倍，特殊情况下（新扩展数组大小已经达到了最大值）则只取最大值
 * 添加元素*/
//	public boolean add(E e) {
//		ensureCapacityInternal(size + 1); //确保elementData数组有合适的大小，进行扩容。
//		elementData[size++] = e;
//		return true;
//	}
/** 指定位置添加一个值，会检查添加的位置和容量*/
//	public void add(int index, E element) {
//		rangeCheckForAdd(index);
//		ensureCapacityInternal(size + 1);  // Increments modCount!!
//		System.arraycopy(elementData, index, elementData, index + 1,
//				size - index);
//		elementData[index] = element;
//		size++;
//	}
/** 说明：remove函数用户移除指定下标的元素，此时会把指定下标到数组末尾的元素向前移动一个单位，
 * 并且会把数组最后一个元素设置为null，这样是为了方便之后将整个数组不被使用时，会被GC，可以作为小的技巧使用。
 */
//	public E remove(int index) {
//		rangeCheck(index); // 检查索引是否合法
//		modCount++; //修改次数
//		E oldValue = elementData(index);
//		int numMoved = size - index - 1; // 需要移动的元素的个数
//		if (numMoved > 0)
//			System.arraycopy(elementData, index+1, elementData, index,
//					numMoved);
//		elementData[--size] = null; // 赋值为空，有利于进行GC
//		return oldValue;
//	}
/** 删除指定元素首次出现的位置*/
//	public boolean remove(Object o) {
//		if (o == null) {
//			for (int index = 0; index < size; index++)
//				if (elementData[index] == null) {
//					fastRemove(index);
//					return true;
//				}
//		} else {
//			for (int index = 0; index < size; index++)
//				if (o.equals(elementData[index])) {
//					fastRemove(index);
//					return true;
//				}
//		}
//		return false;
//	}
/** 快速删除指定位置的值，之所以叫快速，应该是不需要检查和返回值，因为只内部使用*/
//	private void fastRemove(int index) {
//		modCount++;
//		int numMoved = size - index - 1;
//		if (numMoved > 0)
//			System.arraycopy(elementData, index+1, elementData, index,
//					numMoved);
//		elementData[--size] = null; // clear to let GC do its work
//	}
/** 清空数组，把每一个值设为null,方便垃圾回收(不同于reset，数组默认大小有改变的话不会重置)*/
//	public void clear() {
//		modCount++;
//		for (int i = 0; i < size; i++)
//			elementData[i] = null;
//		size = 0;
//	}
/** 添加一个集合的元素到末端，若要添加的集合为空返回false*/
//	public boolean addAll(Collection<? extends E> c) {
//		Object[] a = c.toArray();
//		int numNew = a.length;
//		ensureCapacityInternal(size + numNew);  // Increments modCount
//		System.arraycopy(a, 0, elementData, size, numNew);
//		size += numNew;
//		return numNew != 0;
//	}
/** 功能同上，从指定位置开始添加*/
//	public boolean addAll(int index, Collection<? extends E> c) {
//		rangeCheckForAdd(index);
//		Object[] a = c.toArray();
//		int numNew = a.length;
//		ensureCapacityInternal(size + numNew);  // Increments modCount
//		int numMoved = size - index;
//		if (numMoved > 0)
//			System.arraycopy(elementData, index, elementData, index + numNew,
//					numMoved);
//		System.arraycopy(a, 0, elementData, index, numNew);
//		size += numNew;
//		return numNew != 0;
//	}
/** 删除指定范围元素。参数为开始删的位置和结束位置*/
//	protected void removeRange(int fromIndex, int toIndex) {
//		modCount++;
//		int numMoved = size - toIndex;
//		System.arraycopy(elementData, toIndex, elementData, fromIndex,
//				numMoved);
//		int newSize = size - (toIndex-fromIndex);
//		for (int i = newSize; i < size; i++) {
//			elementData[i] = null;
//		}
//		size = newSize;
//	}
/** 检查索引是否合法*/
//	private void rangeCheck(int index) {
//		if (index >= size)
//			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
//	}
/** 检查是否溢出*/
//	private void rangeCheckForAdd(int index) {
//		if (index > size || index < 0)
//			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
//	}
/** 抛出的异常的详情*/
//	private String outOfBoundsMsg(int index) {
//		return "Index: "+index+", Size: "+size;
//	}
/** 删除指定集合的元素*/
//	public boolean removeAll(Collection<?> c) {
//		Objects.requireNonNull(c);
//		return batchRemove(c, false);
//	}
/** 仅保留指定集合的元素*/
//	public boolean retainAll(Collection<?> c) {
//		Objects.requireNonNull(c);
//		return batchRemove(c, true);
//	}
/** true时从数组保留指定集合中元素的值，
 *  为false时从数组删除指定集合中元素的值。
 * @return 数组中重复的元素都会被删除(而不是仅删除一次或几次)，有任何删除操作都会返回true
 */
//	private boolean batchRemove(Collection<?> c, boolean complement) {
//		final Object[] elementData = this.elementData;
//		int r = 0, w = 0;
//		boolean modified = false;
//		try {
//			遍历数组，并检查这个集合是否包含对应的值，移动要保留的值到数组前面，w最后值为要保留的元素的数量
//			简单点：若保留，就将相同元素移动到前段；若删除，就将不同元素移动到前段
//			for (; r < size; r++)
//				if (c.contains(elementData[r]) == complement)
//					elementData[w++] = elementData[r];
//		} finally { //确保异常抛出前的部分可以完成期望的操作，而未被遍历的部分会被接到后面
//			if (r != size) { //r!=size表示可能出错了：c.contains(elementData[r])抛出异常
//				System.arraycopy(elementData, r,
//						elementData, w,
//						size - r);
//				w += size - r;
//			}
//			如果w==size：表示全部元素都保留了，所以也就没有删除操作发生，所以会返回false；反之，返回true，并更改数组
//			而w!=size的时候，即使try块抛出异常，也能正确处理异常抛出前的操作，因为w始终为要保留的前段部分的长度，数组也不会因此乱序
//			if (w != size) {
//				for (int i = w; i < size; i++)
//					elementData[i] = null;
//				modCount += size - w; //改变的次数
//				size = w; //新的大小为保留的元素的个数
//				modified = true;
//			}
//		}
//		return modified;
//	}
/** 保存数组实例的状态到一个流（即它序列化）。写入过程数组被更改会抛出异常*/
//	private void writeObject(java.io.ObjectOutputStream s)
//			throws java.io.IOException{
//		int expectedModCount = modCount;
//		s.defaultWriteObject(); //执行默认的反序列化/序列化过程。将当前类的非静态和非瞬态字段写入此流

//		s.writeInt(size); // 写入大小

//		for (int i=0; i<size; i++) { // 按顺序写入所有元素
//			s.writeObject(elementData[i]);
//		}
//
//		if (modCount != expectedModCount) {
//			throw new ConcurrentModificationException();
//		}
//	}
/** 上面是写，这个就是读了。*/
//	private void readObject(java.io.ObjectInputStream s)
//			throws java.io.IOException, ClassNotFoundException {
//		elementData = EMPTY_ELEMENTDATA;
//
//		s.defaultReadObject(); // 执行默认的序列化/反序列化过程
//
//		s.readInt(); // 读入数组长度
//
//		if (size > 0) {
//			ensureCapacityInternal(size);
//
//			Object[] a = elementData;
//			for (int i=0; i<size; i++) { //读入所有元素
//				a[i] = s.readObject();
//			}
//		}
//	}
/** 返回ListIterator，开始位置为指定参数*/
//	public ListIterator<E> listIterator(int index) {
//		if (index < 0 || index > size)
//			throw new IndexOutOfBoundsException("Index: "+index);
//		return new ListItr(index);
//	}
/** 返回ListIterator，开始位置为0*/
//	public ListIterator<E> listIterator() {
//		return new ListItr(0);
//	}
/** 返回普通迭代器*/
//	public Iterator<E> iterator() {
//		return new Itr();
//	}
/** 通用的迭代器实现*/
//private class Itr implements Iterator<E> {
//	int cursor;       //游标，下一个元素的索引，默认初始化为0
//	int lastRet = -1; //上次访问的元素的位置
//	int expectedModCount = modCount; //迭代过程不运行修改数组，否则就抛出异常
//
//	public boolean hasNext() { //是否还有下一个
//		return cursor != size;
//	}
//
//	@SuppressWarnings("unchecked")
//	public E next() { //下一个元素
//		checkForComodification(); //检查数组是否被修改
//		int i = cursor;
//		if (i >= size)
//			throw new NoSuchElementException();
//		Object[] elementData = ArrayList.this.elementData;
//		if (i >= elementData.length)
//			throw new ConcurrentModificationException();
//		cursor = i + 1; //向后移动游标
//		return (E) elementData[lastRet = i]; //设置访问的位置并返回这个值
//	}
/** 删除元素*/
//	public void remove() {
//		if (lastRet < 0)
//			throw new IllegalStateException();
//		checkForComodification();
//
//		try {
//			ArrayList.this.remove(lastRet);
//			cursor = lastRet;
//			lastRet = -1;
//			expectedModCount = modCount;
//		} catch (IndexOutOfBoundsException ex) {
//			throw new ConcurrentModificationException();
//		}
//	}
//
//	@Override
//	@SuppressWarnings("unchecked")
//	public void forEachRemaining(Consumer<? super E> consumer) {
//		Objects.requireNonNull(consumer);
//		final int size = ArrayList.this.size;
//		int i = cursor;
//		if (i >= size) {
//			return;
//		}
//		final Object[] elementData = ArrayList.this.elementData;
//		if (i >= elementData.length) {
//			throw new ConcurrentModificationException();
//		}
//		while (i != size && modCount == expectedModCount) {
//			consumer.accept((E) elementData[i++]);
//		}
//		// update once at end of iteration to reduce heap write traffic
//		cursor = i;
//		lastRet = i - 1;
//		checkForComodification();
//	}
//
//	final void checkForComodification() {
//		if (modCount != expectedModCount)
//			throw new ConcurrentModificationException();
//	}
//}
/** ListIterator迭代器实现*/
//private class ListItr extends Itr implements ListIterator<E> {
//	ListItr(int index) {
//		super();
//		cursor = index;
//	}
//
//	public boolean hasPrevious() {
//		return cursor != 0;
//	}
//
//	public int nextIndex() {
//		return cursor;
//	}
//
//	public int previousIndex() {
//		return cursor - 1;
//	}
//
//	@SuppressWarnings("unchecked")
//	public E previous() {
//		checkForComodification();
//		int i = cursor - 1;
//		if (i < 0)
//			throw new NoSuchElementException();
//		Object[] elementData = ArrayList.this.elementData;
//		if (i >= elementData.length)
//			throw new ConcurrentModificationException();
//		cursor = i;
//		return (E) elementData[lastRet = i];
//	}

//	public void set(E e) {
//		if (lastRet < 0)
//			throw new IllegalStateException();
//		checkForComodification();
//		try {
//			ArrayList.this.set(lastRet, e);
//		} catch (IndexOutOfBoundsException ex) {
//			throw new ConcurrentModificationException();
//		}
//	}
//
//	public void add(E e) {
//		checkForComodification();
//
//		try {
//			int i = cursor;
//			ArrayList.this.add(i, e);
//			cursor = i + 1;
//			lastRet = -1;
//			expectedModCount = modCount;
//		} catch (IndexOutOfBoundsException ex) {
//			throw new ConcurrentModificationException();
//		}
//	}
//}
/** 返回指定范围的子数组*/
//	public List<E> subList(int fromIndex, int toIndex) {
//		subListRangeCheck(fromIndex, toIndex, size);
//		return new SubList(this, 0, fromIndex, toIndex);
//	}
/** 安全检查*/
//	static void subListRangeCheck(int fromIndex, int toIndex, int size) {
//		if (fromIndex < 0)
//			throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
//		if (toIndex > size)
//			throw new IndexOutOfBoundsException("toIndex = " + toIndex);
//		if (fromIndex > toIndex)
//			throw new IllegalArgumentException("fromIndex(" + fromIndex +
//					") > toIndex(" + toIndex + ")");
//	}
/** 子数组*/
//  private class SubList extends AbstractList<E> implements RandomAccess {
//	private final AbstractList<E> parent;
//	private final int parentOffset;
//	private final int offset;
//	int size;
//
//	SubList(AbstractList<E> parent,
//			int offset, int fromIndex, int toIndex) {
//		this.parent = parent;
//		this.parentOffset = fromIndex;
//		this.offset = offset + fromIndex;
//		this.size = toIndex - fromIndex;
//		this.modCount = ArrayList.this.modCount;
//	}

//	public E set(int index, E e) {
//		rangeCheck(index); // 检验索引是否合法
//		checkForComodification();
//		E oldValue = ArrayList.this.elementData(offset + index);
//		ArrayList.this.elementData[offset + index] = e;
//		return oldValue;
//	}
//
//	public E get(int index) {
//		rangeCheck(index);
//		checkForComodification();
//		return ArrayList.this.elementData(offset + index);
//	}
//
//	public int size() {
//		checkForComodification();
//		return this.size;
//	}
//
//	public void add(int index, E e) {
//		rangeCheckForAdd(index);
//		checkForComodification();
//		parent.add(parentOffset + index, e);
//		this.modCount = parent.modCount;
//		this.size++;
//	}
//
//	public E remove(int index) {
//		rangeCheck(index);
//		checkForComodification();
//		E result = parent.remove(parentOffset + index);
//		this.modCount = parent.modCount;
//		this.size--;
//		return result;
//	}
//
//	protected void removeRange(int fromIndex, int toIndex) {
//		checkForComodification();
//		parent.removeRange(parentOffset + fromIndex,
//				parentOffset + toIndex);
//		this.modCount = parent.modCount;
//		this.size -= toIndex - fromIndex;
//	}
//
//	public boolean addAll(Collection<? extends E> c) {
//		return addAll(this.size, c);
//	}
//
//	public boolean addAll(int index, Collection<? extends E> c) {
//		rangeCheckForAdd(index);
//		int cSize = c.size();
//		if (cSize==0)
//			return false;
//
//		checkForComodification();
//		parent.addAll(parentOffset + index, c);
//		this.modCount = parent.modCount;
//		this.size += cSize;
//		return true;
//	}
//
//	public Iterator<E> iterator() {
//		return listIterator();
//	}
//
//	public ListIterator<E> listIterator(final int index) {
//		checkForComodification();
//		rangeCheckForAdd(index);
//		final int offset = this.offset;
//
//		return new ListIterator<E>() {
//			int cursor = index;
//			int lastRet = -1;
//			int expectedModCount = ArrayList.this.modCount;
//
//			public boolean hasNext() {
//				return cursor != SubList.this.size;
//			}
//
//			@SuppressWarnings("unchecked")
//			public E next() {
//				checkForComodification();
//				int i = cursor;
//				if (i >= SubList.this.size)
//					throw new NoSuchElementException();
//				Object[] elementData = ArrayList.this.elementData;
//				if (offset + i >= elementData.length)
//					throw new ConcurrentModificationException();
//				cursor = i + 1;
//				return (E) elementData[offset + (lastRet = i)];
//			}
//
//			public boolean hasPrevious() {
//				return cursor != 0;
//			}
//
//			@SuppressWarnings("unchecked")
//			public E previous() {
//				checkForComodification();
//				int i = cursor - 1;
//				if (i < 0)
//					throw new NoSuchElementException();
//				Object[] elementData = ArrayList.this.elementData;
//				if (offset + i >= elementData.length)
//					throw new ConcurrentModificationException();
//				cursor = i;
//				return (E) elementData[offset + (lastRet = i)];
//			}
//
//			@SuppressWarnings("unchecked")
//			public void forEachRemaining(Consumer<? super E> consumer) {
//				Objects.requireNonNull(consumer);
//				final int size = SubList.this.size;
//				int i = cursor;
//				if (i >= size) {
//					return;
//				}
//				final Object[] elementData = ArrayList.this.elementData;
//				if (offset + i >= elementData.length) {
//					throw new ConcurrentModificationException();
//				}
//				while (i != size && modCount == expectedModCount) {
//					consumer.accept((E) elementData[offset + (i++)]);
//				}
//				// update once at end of iteration to reduce heap write traffic
//				lastRet = cursor = i;
//				checkForComodification();
//			}
//
//			public int nextIndex() {
//				return cursor;
//			}
//
//			public int previousIndex() {
//				return cursor - 1;
//			}
//
//			public void remove() {
//				if (lastRet < 0)
//					throw new IllegalStateException();
//				checkForComodification();
//
//				try {
//					SubList.this.remove(lastRet);
//					cursor = lastRet;
//					lastRet = -1;
//					expectedModCount = ArrayList.this.modCount;
//				} catch (IndexOutOfBoundsException ex) {
//					throw new ConcurrentModificationException();
//				}
//			}
//
//			public void set(E e) {
//				if (lastRet < 0)
//					throw new IllegalStateException();
//				checkForComodification();
//
//				try {
//					ArrayList.this.set(offset + lastRet, e);
//				} catch (IndexOutOfBoundsException ex) {
//					throw new ConcurrentModificationException();
//				}
//			}
//
//			public void add(E e) {
//				checkForComodification();
//
//				try {
//					int i = cursor;
//					SubList.this.add(i, e);
//					cursor = i + 1;
//					lastRet = -1;
//					expectedModCount = ArrayList.this.modCount;
//				} catch (IndexOutOfBoundsException ex) {
//					throw new ConcurrentModificationException();
//				}
//			}
//
//			final void checkForComodification() {
//				if (expectedModCount != ArrayList.this.modCount)
//					throw new ConcurrentModificationException();
//			}
//		};
//	}
//
//	public List<E> subList(int fromIndex, int toIndex) {
//		subListRangeCheck(fromIndex, toIndex, size);
//		return new SubList(this, offset, fromIndex, toIndex);
//	}
//
//	private void rangeCheck(int index) {
//		if (index < 0 || index >= this.size)
//			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
//	}
//
//	private void rangeCheckForAdd(int index) {
//		if (index < 0 || index > this.size)
//			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
//	}
//
//	private String outOfBoundsMsg(int index) {
//		return "Index: "+index+", Size: "+this.size;
//	}
/** 修改次数*/
//	private void checkForComodification() {
//		if (ArrayList.this.modCount != this.modCount)
//			throw new ConcurrentModificationException();
//	}
//
//	public Spliterator<E> spliterator() {
//		checkForComodification();
//		return new ArrayListSpliterator<E>(ArrayList.this, offset,
//				offset + this.size, this.modCount);
//	}
//}
//
//	@Override
//	public void forEach(Consumer<? super E> action) {
//		Objects.requireNonNull(action);
//		final int expectedModCount = modCount;
//		@SuppressWarnings("unchecked")
//		final E[] elementData = (E[]) this.elementData;
//		final int size = this.size;
//		for (int i=0; modCount == expectedModCount && i < size; i++) {
//			action.accept(elementData[i]);
//		}
//		if (modCount != expectedModCount) {
//			throw new ConcurrentModificationException();
//		}
//	}
//
//	@Override
//	public Spliterator<E> spliterator() {
//		return new ArrayListSpliterator<>(this, 0, -1, 0);
//	}
//
//  static final class ArrayListSpliterator<E> implements Spliterator<E> {
//
//	private final ArrayList<E> list;
//	private int index; // current index, modified on advance/split
//	private int fence; // -1 until used; then one past last index
//	private int expectedModCount; // initialized when fence set
//
//	ArrayListSpliterator(ArrayList<E> list, int origin, int fence,
//						 int expectedModCount) {
//		this.list = list; // OK if null unless traversed
//		this.index = origin;
//		this.fence = fence;
//		this.expectedModCount = expectedModCount;
//	}
//
//	private int getFence() { // initialize fence to size on first use
//		int hi; // (a specialized variant appears in method forEach)
//		ArrayList<E> lst;
//		if ((hi = fence) < 0) {
//			if ((lst = list) == null)
//				hi = fence = 0;
//			else {
//				expectedModCount = lst.modCount;
//				hi = fence = lst.size;
//			}
//		}
//		return hi;
//	}
//
//	public ArrayListSpliterator<E> trySplit() {
//		int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
//		return (lo >= mid) ? null : // divide range in half unless too small
//				new ArrayListSpliterator<E>(list, lo, index = mid,
//						expectedModCount);
//	}
//
//	public boolean tryAdvance(Consumer<? super E> action) {
//		if (action == null)
//			throw new NullPointerException();
//		int hi = getFence(), i = index;
//		if (i < hi) {
//			index = i + 1;
//			@SuppressWarnings("unchecked") E e = (E)list.elementData[i];
//			action.accept(e);
//			if (list.modCount != expectedModCount)
//				throw new ConcurrentModificationException();
//			return true;
//		}
//		return false;
//	}
//
//	public void forEachRemaining(Consumer<? super E> action) {
//		int i, hi, mc; // hoist accesses and checks from loop
//		ArrayList<E> lst; Object[] a;
//		if (action == null)
//			throw new NullPointerException();
//		if ((lst = list) != null && (a = lst.elementData) != null) {
//			if ((hi = fence) < 0) {
//				mc = lst.modCount;
//				hi = lst.size;
//			}
//			else
//				mc = expectedModCount;
//			if ((i = index) >= 0 && (index = hi) <= a.length) {
//				for (; i < hi; ++i) {
//					@SuppressWarnings("unchecked") E e = (E) a[i];
//					action.accept(e);
//				}
//				if (lst.modCount == mc)
//					return;
//			}
//		}
//		throw new ConcurrentModificationException();
//	}
//
//	public long estimateSize() {
//		return (long) (getFence() - index);
//	}
//
//	public int characteristics() {
//		return Spliterator.ORDERED | Spliterator.SIZED | Spliterator.SUBSIZED;
//	}
//}
//
//	@Override
//	public boolean removeIf(Predicate<? super E> filter) {
//		Objects.requireNonNull(filter);
//		// figure out which elements are to be removed
//		// any exception thrown from the filter predicate at this stage
//		// will leave the collection unmodified
//		int removeCount = 0;
//		final BitSet removeSet = new BitSet(size);
//		final int expectedModCount = modCount;
//		final int size = this.size;
//		for (int i=0; modCount == expectedModCount && i < size; i++) {
//			@SuppressWarnings("unchecked")
//			final E element = (E) elementData[i];
//			if (filter.test(element)) {
//				removeSet.set(i);
//				removeCount++;
//			}
//		}
//		if (modCount != expectedModCount) {
//			throw new ConcurrentModificationException();
//		}
//
//		// shift surviving elements left over the spaces left by removed elements
//		final boolean anyToRemove = removeCount > 0;
//		if (anyToRemove) {
//			final int newSize = size - removeCount;
//			for (int i=0, j=0; (i < size) && (j < newSize); i++, j++) {
//				i = removeSet.nextClearBit(i);
//				elementData[j] = elementData[i];
//			}
//			for (int k=newSize; k < size; k++) {
//				elementData[k] = null;  // Let gc do its work
//			}
//			this.size = newSize;
//			if (modCount != expectedModCount) {
//				throw new ConcurrentModificationException();
//			}
//			modCount++;
//		}
//
//		return anyToRemove;
//	}
//
//	@Override
//	@SuppressWarnings("unchecked")
//	public void replaceAll(UnaryOperator<E> operator) {
//		Objects.requireNonNull(operator);
//		final int expectedModCount = modCount;
//		final int size = this.size;
//		for (int i=0; modCount == expectedModCount && i < size; i++) {
//			elementData[i] = operator.apply((E) elementData[i]);
//		}
//		if (modCount != expectedModCount) {
//			throw new ConcurrentModificationException();
//		}
//		modCount++;
//	}
//
//	@Override
//	@SuppressWarnings("unchecked")
//	public void sort(Comparator<? super E> c) {
//		final int expectedModCount = modCount;
//		Arrays.sort((E[]) elementData, 0, size, c);
//		if (modCount != expectedModCount) {
//			throw new ConcurrentModificationException();
//		}
//		modCount++;
//	}
//}