package util;

/**
 * @author fxf
 * @create 2017-09-14 9:41
 **/

public class LinkedListDeep {
	public static void main(String[] args) {

	}
}

/**
 * 引用博客：http://blog.csdn.net/anxpp/article/details/51203591
 * 源码解读
 * 双向链表实现的List。
 */
/**
 *1 	 LinkedList底层使用双向链表，实现了List和deque。实现所有的可选List操作，并可以只有所有元素（包括空值）
 *   	 其大小理论上仅受内存大小的限制
 *
 *2		所有的操作都可以作为一个双联列表来执行（及对双向链表操作）。
 * 		把对链表的操作封装起来，并对外提供看起来是对普通列表操作的方法。
 * 		遍历从起点、终点、或指定位置开始
 * 		内部方法，注释会描述为节点的操作(如删除第一个节点)，公开的方法会描述为元素的操作(如删除第一个元素)
 *
 * 3	LinkedList不是线程安全的，如果在多线程中使用（修改），需要在外部作同步处理。
 *
 * 4	需要弄清元素（节点）的索引和位置的区别，不然有几个地方不好理解，具体在碰到的地方会解释。
 *
 * 5	迭代器可以快速报错
 */
/** 通过LinkedList实现的接口可知，其支持队列操作，双向列表操作，能被克隆，支持序列化*/
//public class LinkedList<E> extends AbstractSequentialList<E> implements List<E>, Deque<E>, Cloneable, Serializable {
/**  LinkedList的大小（指其所含的元素个数）*/
//	transient int size;
/**
 * 指向第一个节点
 * 不变的: (first == null && last == null) ||
 *            (first.prev == null && first.item != null)
 */
//	transient LinkedList.Node<E> first;
/**
 * 指向最后一个节点
 * 不变的: (first == null && last == null) ||
 *            (last.next == null && last.item != null)
 */
//	transient LinkedList.Node<E> last;
/**
 * (1) first 是双向链表的表头，它是双向链表节点所对应的类Node的实例

 (2) last 是双向链表的最后一个元素，它是双向链表节点所对应的类Node的实例

 (3) size 是双向链表中节点的个数。
 */
/** 序列id*/
//	private static final long serialVersionUID = 876323262645176354L;
/**构建一个空列表*/
//	public LinkedList() {
//		this.size = 0;
//	}
/**构造一个包含指定collection的元素的列表，这些元素按照该collection的迭代器返回的顺序排列的*/
//	public LinkedList(Collection<? extends E> var1) {
//		this();
//		this.addAll(var1);
//	}
/**头插入，即将节点值为e的节点设置为链表首节点*/
//	private void linkFirst(E var1) {
//		LinkedList.Node var2 = this.first;//得到首节点
//		LinkedList.Node var3 = new LinkedList.Node((LinkedList.Node)null, var1, var2);//创建一个节点
//		this.first = var3;//设置首节点
//		if(var2 == null) {
//			this.last = var3;//如果之前首节点为空(size==0)，那么尾节点就是首节点
//		} else {
//			var2.prev = var3;//如果之前首节点不为空，之前的首节点的前一个节点为当前首节点
//		}
//		++this.size;//长度+1
//		++this.modCount;//修改次数+1
//	}
/**尾插入，即将节点值为e的节点设置为链表的尾节点*/
//	void linkLast(E var1) {
//		LinkedList.Node var2 = this.last;//得到尾节点
//		LinkedList.Node var3 = new LinkedList.Node(var2, var1, (LinkedList.Node)null);//使用参数创建一个节点
//		this.last = var3;//设置尾节点
//		if(var2 == null) {
//			this.first = var3;//如果之前尾节点为空(size==0)，首节点即尾节点
//		} else {
//			var2.next = var3;//如果之前尾节点不为空，之前的尾节点的后一个就是当前的尾节点
//		}
//
//		++this.size;
//		++this.modCount;
//	}
/***中间插入，在非空节点succ之前插入节点值e*/
//	void linkBefore(E var1, LinkedList.Node<E> var2) {
//		LinkedList.Node var3 = var2.prev;
//		LinkedList.Node var4 = new LinkedList.Node(var3, var1, var2);
//		var2.prev = var4;
//		if(var3 == null) {
//			this.first = var4;
//		} else {
//			var3.next = var4;
//		}
//
//		++this.size;
//		++this.modCount;
//	}
/**删除首节点并返回删除前首节点的值，内部使用*/
//	private E unlinkFirst(LinkedList.Node<E> var1) {
//		Object var2 = var1.item; //获取首节点的值
//		LinkedList.Node var3 = var1.next;//得到下一个节点
//		var1.item = null;
//		var1.next = null;//便于垃圾回收期清理
//		this.first = var3;//首节点的下一个节点成为新的首节点
//		if(var3 == null) {
//			this.last = null;//如果不存在下一个节点，则首尾都为null(空表)
//		} else {
//			var3.prev = null;//如果存在下一个节点，那它向前指向null
//		}
//
//		--this.size;
//		++this.modCount;
//		return var2;
//	}
/**删除尾节点并返回删除前尾节点的值，内部使用*/
//	private E unlinkLast(LinkedList.Node<E> var1) {
//		Object var2 = var1.item;//获取值
//		LinkedList.Node var3 = var1.prev;//获取尾节点前一个节点
//		var1.item = null;
//		var1.prev = null;//便于垃圾回收期清理
//		this.last = var3;//前一个节点成为新的尾节点
//		if(var3 == null) {
//			this.first = null;//如果前一个节点不存在，则首尾都为null(空表)
//		} else {
//			var3.next = null;//如果前一个节点存在，先后指向null
//		}
//		--this.size;
//		++this.modCount;
//		return var2;
//	}
/**删除指定节点并返回被删除的元素值*/
//	E unlink(LinkedList.Node<E> var1) {
//		Object var2 = var1.item;//获取当前值和前后节点
//		LinkedList.Node var3 = var1.next;
//		LinkedList.Node var4 = var1.prev;
//		if(var4 == null) {
//			this.first = var3;//如果前一个节点为空(如当前节点为首节点)，后一个节点成为新的首节点
//		} else {
//			var4.next = var3;//如果前一个节点不为空，那么他先后指向当前的下一个节点
//			var1.prev = null;//方便gc回收
//		}
//
//		if(var3 == null) {
//			this.last = var4;//如果后一个节点为空(如当前节点为尾节点)，当前节点前一个成为新的尾节点
//		} else {
//			var3.prev = var4;//如果后一个节点不为空，后一个节点向前指向当前的前一个节点
//			var1.next = null;//方便gc回收
//		}
//
//		var1.item = null;//方便gc回收
//		--this.size;
//		++this.modCount;
//		return var2;
//	}
/**获取第一个元素*/
//	public E getFirst() {
//		LinkedList.Node var1 = this.first;//得到首节点
//		if(var1 == null) {//如果为空，抛出异常
//			throw new NoSuchElementException();
//		} else {
//			return var1.item;
//		}
//	}
/**获取最后一个元素*/
//	public E getLast() {
//		LinkedList.Node var1 = this.last;//得到尾节点
//		if(var1 == null) {//如果为空，抛出异常
//			throw new NoSuchElementException();
//		} else {
//			return var1.item;
//		}
//	}
/**删除第一个元素并返回删除的元素*/
//	public E removeFirst() {//得到最后一个节点
//		LinkedList.Node var1 = this.first;
//		if(var1 == null) {//如果为空，抛出异常
//			throw new NoSuchElementException();
//		} else {
//			return this.unlinkFirst(var1);
//		}
//	}
/**删除最后一个元素并返回删除的值*/
//	public E removeLast() {
//		LinkedList.Node var1 = this.last;
//		if(var1 == null) {
//			throw new NoSuchElementException();
//		} else {
//			return this.unlinkLast(var1);
//		}
//	}
/**添加元素作为第一个元素*/
//	public void addFirst(E var1) {
//		this.linkFirst(var1);
//	}
/**添加元素作为最后一个元素*/
//	public void addLast(E var1) {
//		this.linkLast(var1);
//	}
/**检查是否包含某个元素，返回bool*/
//	public boolean contains(Object var1) {
//		return this.indexOf(var1) != -1;//返回指定元素的索引位置，不存在就返回-1，然后比较返回bool值
//	}
/**返回列表长度*/
//	public int size() {
//		return this.size;
//	}
/**添加一个元素，默认添加到末尾作为最后一个元素*/
//	public boolean add(E var1) {
//		this.linkLast(var1);
//		return true;
//	}
/**删除指定元素，默认从first节点开始，删除第一次出现的那个元素*/
//	public boolean remove(Object var1) {
//		LinkedList.Node var2;
//		if(var1 == null) {//会根据是否为null分开处理。若值不是null，会用到对象的equals()方法
//			for(var2 = this.first; var2 != null; var2 = var2.next) {
//				if(var2.item == null) {
//					this.unlink(var2);
//					return true;
//				}
//			}
//		} else {
//			for(var2 = this.first; var2 != null; var2 = var2.next) {
//				if(var1.equals(var2.item)) {
//					this.unlink(var2);
//					return true;
//				}
//			}
//		}
//
//		return false;
//	}
/**添加指定集合的元素到列表，默认从最后开始添加*/
//	public boolean addAll(Collection<? extends E> var1) {
//		return this.addAll(this.size, var1);
//	}
/**
 * 1 从指定位置（而不是下标！下标即索引从0开始，位置可以看做从1开始，其实也是0）后面添加指定集合的元素到列表中，只要有至少一次添加就会返回true
 * 2 index换成position应该会更好理解，所以也就是从索引为index(position)的元素的前面索引为index-1的后面添加！
 * 3 当然位置可以为0啊，为0的时候就是从位置0(虽然它不存在)后面开始添加嘛，所以理所当前就是添加到第一个位置（位置1的前面）的前面了啊！
 * 4 比如列表：0 1 2 3，如果此处index=4(实际索引为3)，就是在元素3后面添加；如果index=3(实际索引为2)，就在元素2后面添加。
 */
//	public boolean addAll(int var1, Collection<? extends E> var2) {
//		this.checkPositionIndex(var1);//检查索引是否正确（0<=index<=size）
//		Object[] var3 = var2.toArray(); //得到元素数组
//		int var4 = var3.length;//得到元素个数
//		if(var4 == 0) {//若没有元素要添加，直接返回false
//			return false;
//		} else {
//			LinkedList.Node var5;
//			LinkedList.Node var6;
//			if(var1 == this.size) {//如果是在末尾开始添加，当前节点后一个节点初始化为null，前一个节点为尾节点
//				var6 = null;//这里可以看做node(index)，不过index=size了（index最大只能是size-1），所以这里的succ只能=null，也方便后面判断
//				var5 = this.last;//这里看做noede(index-1)，当然实现是不能这么写的，看做这样只是为了好理解，所以就是在node(index-1的后面开始添加元素)
//			} else {//如果不是从末尾开始添加，当前位置的节点为指定位置的节点，前一个节点为要添加的节点的前一个节点
//				var6 = this.node(var1);//添加好元素后(整个新加的)的后一个节点
//				var5 = var6.prev;//这里依然是node(index-1)
//			}
//
//			Object[] var7 = var3;
//			int var8 = var3.length;
//			//遍历数组并添加到列表中
//			for(int var9 = 0; var9 < var8; ++var9) {
//				Object var10 = var7[var9];
//				LinkedList.Node var12 = new LinkedList.Node(var5, var10, (LinkedList.Node)null);//创建一个节点，向前指向上面得到的前节点
//				if(var5 == null) {
//					this.first = var12;//若果前节点为null，则新加的节点为首节点
//				} else {
//					var5.next = var12;//如果存在前节点，前节点会向后指向新加的节点
//				}
//
//				var5 = var12;//新加的节点成为前一个节点
//			}
//
//			if(var6 == null) {
				//pred.next = null  //加上这句也可以更好的理解
//				this.last = var5; //如果是从最后开始添加的，则最后添加的节点成为尾节点
//			} else {
//				var5.next = var6;//如果不是从最后开始添加的，则最后添加的节点向后指向之前得到的后续第一个节点
//				var6.prev = var5;//当前，后续的第一个节点也应改为向前指向最后一个添加的节点
//			}
//
//			this.size += var4;
//			++this.modCount;
//			return true;
//		}
//	}
/**清空表*/
//	public void clear() {
//		LinkedList.Node var2;
//		for(LinkedList.Node var1 = this.first; var1 != null; var1 = var2) {//方便gc回收垃圾
//			var2 = var1.next;
//			var1.item = null;
//			var1.next = null;
//			var1.prev = null;
//		}
//
//		this.first = this.last = null;
//		this.size = 0;
//		++this.modCount;
//	}
/**获取指定索引的节点的值*/
//	public E get(int var1) {
//		this.checkElementIndex(var1);
//		return this.node(var1).item;
//	}
/**修改指定索引的值并返回之前的值*/
//	public E set(int var1, E var2) {
//		this.checkElementIndex(var1);
//		LinkedList.Node var3 = this.node(var1);
//		Object var4 = var3.item;
//		var3.item = var2;
//		return var4;
//	}
/**指定位置后面（即索引为这个值的元素的前面）添加元素*/
//	public void add(int var1, E var2) {
//		this.checkPositionIndex(var1);
//		if(var1 == this.size) {
//			this.linkLast(var2);//如果指定位置为最后，则添加到链表最后
//		} else {//如果指定位置不是最后，则添加到指定位置前
//			this.linkBefore(var2, this.node(var1));
//		}
//
//	}
/**删除指定位置的元素，*/
//	public E remove(int var1) {
//		this.checkElementIndex(var1);
//		return this.unlink(this.node(var1));
//	}
/**检查索引是否超出范围，因为元素索引是0~size-1的，所以index必须满足0<=index<size*/
//	private boolean isElementIndex(int var1) {
//		return var1 >= 0 && var1 < this.size;
//	}
/**检查位置是否超出范围，index必须在index~size之间（含），如果超出，返回false*/
//	private boolean isPositionIndex(int var1) {
//		return var1 >= 0 && var1 <= this.size;
//	}
/**异常详情*/
//	private String outOfBoundsMsg(int var1) {
//		return "Index: " + var1 + ", Size: " + this.size;
//	}
/**检查元素索引是否超出范围，若已超出，就抛出异常*/
//	private void checkElementIndex(int var1) {
//		if(!this.isElementIndex(var1)) {
//			throw new IndexOutOfBoundsException(this.outOfBoundsMsg(var1));
//		}
//	}
/**检查位置是否超出范围，若已超出，就抛出异常*/
//	private void checkPositionIndex(int var1) {
//		if(!this.isPositionIndex(var1)) {
//			throw new IndexOutOfBoundsException(this.outOfBoundsMsg(var1));
//		}
//	}
/**获取指定位置的节点*/
//	LinkedList.Node<E> node(int var1) {
//		LinkedList.Node var2;
//		int var3;
//		if(var1 < this.size >> 1) {如果位置索引小于列表长度的一半(或一半减一)，从前面开始遍历；否则，从后面开始遍历
//			var2 = this.first;//index==0时不会循环，直接返回first
//
//			for(var3 = 0; var3 < var1; ++var3) {
//				var2 = var2.next;
//			}
//
//			return var2;
//		} else {
//			var2 = this.last;
//
//			for(var3 = this.size - 1; var3 > var1; --var3) {
//				var2 = var2.prev;
//			}
//
//			return var2;
//		}
//	}
/**
 *获取指定元素从first开始的索引位置，不存在就返回-1
 * 不能按条件双向找了，所以通常根据索引获得元素的速度比通过元素获得索引的速度快
 */
//	public int indexOf(Object var1) {
//		int var2 = 0;
//		LinkedList.Node var3;
//		if(var1 == null) {
//			for(var3 = this.first; var3 != null; var3 = var3.next) {
//				if(var3.item == null) {
//					return var2;
//				}
//
//				++var2;
//			}
//		} else {
//			for(var3 = this.first; var3 != null; var3 = var3.next) {
//				if(var1.equals(var3.item)) {
//					return var2;
//				}
//
//				++var2;
//			}
//		}
//
//		return -1;
//	}
/**
 * 获取指定元素从first开始最后出现的索引，不存在就返回-1
 * 但实际查找是从last开始的
 */
//	public int lastIndexOf(Object var1) {
//		int var2 = this.size;
//		LinkedList.Node var3;
//		if(var1 == null) {
//			for(var3 = this.last; var3 != null; var3 = var3.prev) {
//				--var2;
//				if(var3.item == null) {
//					return var2;
//				}
//			}
//		} else {
//			for(var3 = this.last; var3 != null; var3 = var3.prev) {
//				--var2;
//				if(var1.equals(var3.item)) {
//					return var2;
//				}
//			}
//		}
//
//		return -1;
//	}
/**
 * 提供普通队列和双向队列的功能，当然，也可以实现栈，FIFO，FILO
 * 出队（从前端），获得第一个元素，不存在会返回null，不会删除元素（节点）
 */
//	public E peek() {
//		LinkedList.Node var1 = this.first;
//		return var1 == null?null:var1.item;
//	}
//出队（从前端），不删除元素，若为null会抛出异常而不是返回null
//	public E element() {
//		return this.getFirst();
//	}
//出队（从前端），如果不存在会返回null，存在的话会返回值并移除这个元素（节点）
//	public E poll() {
//		LinkedList.Node var1 = this.first;
//		return var1 == null?null:this.unlinkFirst(var1);
//	}
//出队（从前端），如果不存在会抛出异常而不是返回null，存在的话会返回值并移除这个元素（节点）
//	public E remove() {
//		return this.removeFirst();
//	}
//入队（从后端），始终返回true
//	public boolean offer(E var1) {
//		return this.add(var1);
//	}
//入队（从前端），始终返回true
//	public boolean offerFirst(E var1) {
//		this.addFirst(var1);
//		return true;
//	}
//入队（从后端），始终返回true
//	public boolean offerLast(E var1) {
//		this.addLast(var1);
//		return true;
//	}
//出队（从前端），获得第一个元素，不存在会返回null，不会删除元素（节点）
//	public E peekFirst() {
//		LinkedList.Node var1 = this.first;
//		return var1 == null?null:var1.item;
//	}
//出队（从后端），获得最后一个元素，不存在会返回null，不会删除元素（节点）
//	public E peekLast() {
//		LinkedList.Node var1 = this.last;
//		return var1 == null?null:var1.item;
//	}
//出队（从前端），获得第一个元素，不存在会返回null，会删除元素（节点）
//	public E pollFirst() {
//		LinkedList.Node var1 = this.first;
//		return var1 == null?null:this.unlinkFirst(var1);
//	}
//出队（从后端），获得最后一个元素，不存在会返回null，会删除元素（节点）
//	public E pollLast() {
//		LinkedList.Node var1 = this.last;
//		return var1 == null?null:this.unlinkLast(var1);
//	}
//入栈，从前面添加
//	public void push(E var1) {
//		this.addFirst(var1);
//	}
//出栈，返回栈顶元素，从前面移除（会删除）
//	public E pop() {
//		return this.removeFirst();
//	}
//
//	public boolean removeFirstOccurrence(Object var1) {
//		return this.remove(var1);
//	}
//
//	public boolean removeLastOccurrence(Object var1) {
//		LinkedList.Node var2;
//		if(var1 == null) {
//			for(var2 = this.last; var2 != null; var2 = var2.prev) {
//				if(var2.item == null) {
//					this.unlink(var2);
//					return true;
//				}
//			}
//		} else {
//			for(var2 = this.last; var2 != null; var2 = var2.prev) {
//				if(var1.equals(var2.item)) {
//					this.unlink(var2);
//					return true;
//				}
//			}
//		}
//
//		return false;
//	}
//
//	public ListIterator<E> listIterator(int var1) {
//		this.checkPositionIndex(var1);
//		return new LinkedList.ListItr(var1);
//	}
//返回迭代器
//	public Iterator<E> descendingIterator() {
//		return new LinkedList.DescendingIterator();
//	}
//
//	private LinkedList<E> superClone() {
//		try {
//			return (LinkedList)super.clone();
//		} catch (CloneNotSupportedException var2) {
//			throw new InternalError(var2);
//		}
//	}
//返回此 LinkedList实例的浅拷贝
//	public Object clone() {
//		LinkedList var1 = this.superClone();
//		var1.first = var1.last = null;
//		var1.size = 0;
//		var1.modCount = 0;
//
//		for(LinkedList.Node var2 = this.first; var2 != null; var2 = var2.next) {
//			var1.add(var2.item);
//		}
//
//		return var1;
//	}
//返回一个包含LinkedList中所有元素值的数组
//	public Object[] toArray() {
//		Object[] var1 = new Object[this.size];
//		int var2 = 0;
//
//		for(LinkedList.Node var3 = this.first; var3 != null; var3 = var3.next) {
//			var1[var2++] = var3.item;
//		}
//
//		return var1;
//	}
//如果给定的参数数组长度足够，则将ArrayList中所有元素按序存放于参数数组中，并返回
//如果给定的参数数组长度小于LinkedList的长度，则返回一个新分配的、长度等于LinkedList长度的、包含LinkedList中所有元素的新数组
//	public <T> T[] toArray(T[] var1) {
//		if(var1.length < this.size) {
//			var1 = (Object[])((Object[]) Array.newInstance(var1.getClass().getComponentType(), this.size));
//		}
//
//		int var2 = 0;
//		Object[] var3 = var1;
//
//		for(LinkedList.Node var4 = this.first; var4 != null; var4 = var4.next) {
//			var3[var2++] = var4.item;
//		}
//
//		if(var1.length > this.size) {
//			var1[this.size] = null;
//		}
//
//		return var1;
//	}
//序列化：将linkedList的“大小，所有的元素值”都写入到输出流中
//	private void writeObject(ObjectOutputStream var1) throws IOException {
//		var1.defaultWriteObject();
//		var1.writeInt(this.size);
//
//		for(LinkedList.Node var2 = this.first; var2 != null; var2 = var2.next) {
//			var1.writeObject(var2.item);
//		}
//
//	}
//反序列化：先将LinkedList的“大小”读出，然后将“所有的元素值”读出
//	private void readObject(ObjectInputStream var1) throws IOException, ClassNotFoundException {
//		var1.defaultReadObject();
//		int var2 = var1.readInt();
//
//		for(int var3 = 0; var3 < var2; ++var3) {
//			this.linkLast(var1.readObject());
//		}
//
//	}
//
//	public Spliterator<E> spliterator() {
//		return new LinkedList.LLSpliterator(this, -1, 0);
//	}
//因为采用链表实现，所以迭代器很简单
//	private class DescendingIterator implements Iterator<E> {
//		private final LinkedList<E>.ListItr itr;
//
//		private DescendingIterator() {
//			this.itr = LinkedList.this.new ListItr(LinkedList.this.size());
//		}
//
//		public boolean hasNext() {
//			return this.itr.hasPrevious();
//		}
//
//		public E next() {
//			return this.itr.previous();
//		}
//
//		public void remove() {
//			this.itr.remove();
//		}
//	}
//
//	static final class LLSpliterator<E> implements Spliterator<E> {
//		static final int BATCH_UNIT = 1024;
//		static final int MAX_BATCH = 33554432;
//		final LinkedList<E> list;
//		LinkedList.Node<E> current;
//		int est;
//		int expectedModCount;
//		int batch;
//
//		LLSpliterator(LinkedList<E> var1, int var2, int var3) {
//			this.list = var1;
//			this.est = var2;
//			this.expectedModCount = var3;
//		}
//
//		final int getEst() {
//			int var1 = this.est;
//			if(this.est < 0) {
//				LinkedList var2 = this.list;
//				if(this.list == null) {
//					var1 = this.est = 0;
//				} else {
//					this.expectedModCount = var2.modCount;
//					this.current = var2.first;
//					var1 = this.est = var2.size;
//				}
//			}
//
//			return var1;
//		}
//
//		public long estimateSize() {
//			return (long)this.getEst();
//		}
//
//		public Spliterator<E> trySplit() {
//			int var2 = this.getEst();
//			if(var2 > 1) {
//				LinkedList.Node var1 = this.current;
//				if(this.current != null) {
//					int var3 = this.batch + 1024;
//					if(var3 > var2) {
//						var3 = var2;
//					}
//
//					if(var3 > 33554432) {
//						var3 = 33554432;
//					}
//
//					Object[] var4 = new Object[var3];
//					int var5 = 0;
//
//					do {
//						var4[var5++] = var1.item;
//					} while((var1 = var1.next) != null && var5 < var3);
//
//					this.current = var1;
//					this.batch = var5;
//					this.est = var2 - var5;
//					return Spliterators.spliterator(var4, 0, var5, 16);
//				}
//			}
//
//			return null;
//		}
//
//		public void forEachRemaining(Consumer<? super E> var1) {
//			if(var1 == null) {
//				throw new NullPointerException();
//			} else {
//				int var3;
//				if((var3 = this.getEst()) > 0) {
//					LinkedList.Node var2 = this.current;
//					if(this.current != null) {
//						this.current = null;
//						this.est = 0;
//
//						do {
//							Object var4 = var2.item;
//							var2 = var2.next;
//							var1.accept(var4);
//							if(var2 == null) {
//								break;
//							}
//
//							--var3;
//						} while(var3 > 0);
//					}
//				}
//
//				if(this.list.modCount != this.expectedModCount) {
//					throw new ConcurrentModificationException();
//				}
//			}
//		}
//
//		public boolean tryAdvance(Consumer<? super E> var1) {
//			if(var1 == null) {
//				throw new NullPointerException();
//			} else {
//				if(this.getEst() > 0) {
//					LinkedList.Node var2 = this.current;
//					if(this.current != null) {
//						--this.est;
//						Object var3 = var2.item;
//						this.current = var2.next;
//						var1.accept(var3);
//						if(this.list.modCount != this.expectedModCount) {
//							throw new ConcurrentModificationException();
//						}
//
//						return true;
//					}
//				}
//
//				return false;
//			}
//		}
//
//		public int characteristics() {
//			return 16464;
//		}
//	}
//
//	private class ListItr implements ListIterator<E> {
//		private LinkedList.Node<E> lastReturned;
//		private LinkedList.Node<E> next;
//		private int nextIndex;
//		private int expectedModCount;
//
//		ListItr(int var2) {
//			this.expectedModCount = LinkedList.this.modCount;
//			this.next = var2 == LinkedList.this.size?null:LinkedList.this.node(var2);
//			this.nextIndex = var2;
//		}
//
//		public boolean hasNext() {
//			return this.nextIndex < LinkedList.this.size;
//		}
//
//		public E next() {
//			this.checkForComodification();
//			if(!this.hasNext()) {
//				throw new NoSuchElementException();
//			} else {
//				this.lastReturned = this.next;
//				this.next = this.next.next;
//				++this.nextIndex;
//				return this.lastReturned.item;
//			}
//		}
//
//		public boolean hasPrevious() {
//			return this.nextIndex > 0;
//		}
//
//		public E previous() {
//			this.checkForComodification();
//			if(!this.hasPrevious()) {
//				throw new NoSuchElementException();
//			} else {
//				this.lastReturned = this.next = this.next == null?LinkedList.this.last:this.next.prev;
//				--this.nextIndex;
//				return this.lastReturned.item;
//			}
//		}
//
//		public int nextIndex() {
//			return this.nextIndex;
//		}
//
//		public int previousIndex() {
//			return this.nextIndex - 1;
//		}
//
//		public void remove() {
//			this.checkForComodification();
//			if(this.lastReturned == null) {
//				throw new IllegalStateException();
//			} else {
//				LinkedList.Node var1 = this.lastReturned.next;
//				LinkedList.this.unlink(this.lastReturned);
//				if(this.next == this.lastReturned) {
//					this.next = var1;
//				} else {
//					--this.nextIndex;
//				}
//
//				this.lastReturned = null;
//				++this.expectedModCount;
//			}
//		}
//
//		public void set(E var1) {
//			if(this.lastReturned == null) {
//				throw new IllegalStateException();
//			} else {
//				this.checkForComodification();
//				this.lastReturned.item = var1;
//			}
//		}
//
//		public void add(E var1) {
//			this.checkForComodification();
//			this.lastReturned = null;
//			if(this.next == null) {
//				LinkedList.this.linkLast(var1);
//			} else {
//				LinkedList.this.linkBefore(var1, this.next);
//			}
//
//			++this.nextIndex;
//			++this.expectedModCount;
//		}
//
//		public void forEachRemaining(Consumer<? super E> var1) {
//			Objects.requireNonNull(var1);
//
//			while(LinkedList.this.modCount == this.expectedModCount && this.nextIndex < LinkedList.this.size) {
//				var1.accept(this.next.item);
//				this.lastReturned = this.next;
//				this.next = this.next.next;
//				++this.nextIndex;
//			}
//
//			this.checkForComodification();
//		}
//
//		final void checkForComodification() {
//			if(LinkedList.this.modCount != this.expectedModCount) {
//				throw new ConcurrentModificationException();
//			}
//		}
//	}
/**
 * 节点Node结构
 */
//	private static class Node<E> {
//		E item; // 当前节点所包含的值
//		LinkedList.Node<E> next; //下一个节点
//		LinkedList.Node<E> prev; //上一个节点
//
//		Node(LinkedList.Node<E> var1, E var2, LinkedList.Node<E> var3) {
//			this.item = var2;
//			this.next = var3;
//			this.prev = var1;
//		}
//	}
//}