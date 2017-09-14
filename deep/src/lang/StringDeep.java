package lang;

/**
 * lang  包
 * @author fxf
 * @create 2017-09-12 15:24
 **/

public class StringDeep {
	public static void main(String[] args) {
		String s1="java java java";
		//indexOf两个匹配问题
		System.out.println(s1.indexOf("java"));//输出0
		System.out.println(s1.indexOf("java",2));//输出5，大于等于2,从2开始搜索起始点
		System.out.println(s1.indexOf("java",9));//输出10，大于等于9，从9开始搜索起始点

		String s2="java java java";
		//接下来是lastIndexOf
		System.out.println(s2.lastIndexOf("java"));//输出为10
		System.out.println(s2.lastIndexOf("java",2));//输出为0，返回值要小于等于2，从2开始，向左边搜索起始点
		System.out.println(s2.lastIndexOf("java",9));//输出为5，返回值要小于等于9，从9开始，向左边搜索起始点
	}
}
/**
 * String 源码
 */
/**
 * 1、不允许其他类继承。这个应该不是最终原因，但这里权且也当成是一个原因。
 * 		String类的内部好多方法的实现都不是Java编程语言本身编写的，好多方法都是调用的操作系统本地的API，这就是著名的“本地方法调用”，
 * 		也只有这样才能做事，这种类是非常底层的，和操作系统交流频繁的，那么如果这种类可以被继承的话，
 * 		如果我们再把它的方法重写了，往操作系统内部写入一段具有恶意攻击性质的代码什么的，这不就成了核心病毒了么？
 * 2、String类中的成员属性也几乎都设计成了private final的，这样String就被设计成一个不变类，这样有助于共享，提高性能。
 * 		可以将字符串对象保存在字符串常量池中以供与字面值相同字符串对象共享。
 * 		如果String对象是可变的，那就不能这样共享，因为一旦对某一个String类型变量引用的对象值改变，
 * 		将同时改变一起共享字符串对象的其他String类型变量所引用的对象的值。
 * 3、String被设计为不变类，其中的offset，value[]都被设计成private final的，
 * 		这样在多线程时，对String对象的访问是可以保证安全的。java程序语言的许多特性依赖于不可变的String对象。
 */
/**
 * 实现了Serializable接口(可以序列化和反序列化),Comparale(可以进行自定义的字符串比较)
 * CharSequence(一个可读序列。此接口对许多不同种类的 char 序列提供统一的只读访问。StringBuilder 和StringBuffer也实现了这个接口)
 */
//public final class String
//		implements java.io.Serializable, Comparable<String>, CharSequence {

/** String是以char数组的形式存储在这里的，而且他是final类型的，证明其不可变（immutable），这也就是为什么String 不可变的原因。*/
//	private final char value[];
//
//	private int hash; //表示string字符的哈希值，默认为0
//
//	private static final long serialVersionUID = -6849794470754667710L;
//
//	private static final ObjectStreamField[] serialPersistentFields =
//			new ObjectStreamField[0];
/** (1)无参构造器
 * 可以看到由于String是不可变的，所以如果使用无参构造器，则完全没有必要！！
 * */
//	public String() {
//		this.value = "".value;
//	}
/**
 * （2）String 参数
 * 可以看到只是将value引用指向original中的value数组，因为两者都是final的，所以这个看来也没那么必要。
 * 因为String s1=new String("s1s1"); String s2=new String(s1);这种用法完全没有必要，而不如直接引用，s2=s1;
 */
//	public String(String original) {
//		this.value = original.value;
//		this.hash = original.hash;
//	}
/**
 * (3)char[]参数
 * Arrays.copyOf功能是实现数组的复制，返回复制后的数组。参数是被复制的数组和复制的长度
 * 可以发现当通过char数组构建时，只是将char数组复制到value中，而且是复制，而不是简单的引用相等。
 */
//	public String(char value[]) {
//		this.value = Arrays.copyOf(value, value.length);
//	}
/**
 * 与上面的区别是，这里只是利用char数组中的一部分来构建String，其中offset代表起始下标，count是所有构建的长度。
 */
//	public String(char value[], int offset, int count) {
//		if (offset < 0) {
//			throw new StringIndexOutOfBoundsException(offset);
//		}
//		if (count <= 0) {
//			if (count < 0) {
//				throw new StringIndexOutOfBoundsException(count);
//			}
//			if (offset <= value.length) {
//				this.value = "".value;
//				return;
//			}
//		}
//		if (offset > value.length - count) {
//			throw new StringIndexOutOfBoundsException(offset + count);
//		}
//		this.value = Arrays.copyOfRange(value, offset, offset+count);
//	}
//
//	public String(int[] codePoints, int offset, int count) {
//		if (offset < 0) {
//			throw new StringIndexOutOfBoundsException(offset);
//		}
//		if (count <= 0) {
//			if (count < 0) {
//				throw new StringIndexOutOfBoundsException(count);
//			}
//			if (offset <= codePoints.length) {
//				this.value = "".value;
//				return;
//			}
//		}
//		if (offset > codePoints.length - count) {
//			throw new StringIndexOutOfBoundsException(offset + count);
//		}
//
//		final int end = offset + count;
//
//		int n = count;
//		for (int i = offset; i < end; i++) {
//			int c = codePoints[i];
//			if (Character.isBmpCodePoint(c))
//				continue;
//			else if (Character.isValidCodePoint(c))
//				n++;
//			else throw new IllegalArgumentException(Integer.toString(c));
//		}
//
//		final char[] v = new char[n];
//
//		for (int i = offset, j = 0; i < end; i++, j++) {
//			int c = codePoints[i];
//			if (Character.isBmpCodePoint(c))
//				v[j] = (char)c;
//			else
//				Character.toSurrogates(c, v, j++);
//		}
//		this.value = v;
//	}
//
//	@Deprecated
//	public String(byte ascii[], int hibyte, int offset, int count) {
//		checkBounds(ascii, offset, count);
//		char value[] = new char[count];
//
//		if (hibyte == 0) {
//			for (int i = count; i-- > 0;) {
//				value[i] = (char)(ascii[i + offset] & 0xff);
//			}
//		} else {
//			hibyte <<= 8;
//			for (int i = count; i-- > 0;) {
//				value[i] = (char)(hibyte | (ascii[i + offset] & 0xff));
//			}
//		}
//		this.value = value;
//	}
//
//	@Deprecated
//	public String(byte ascii[], int hibyte) {
//		this(ascii, hibyte, 0, ascii.length);
//	}
//
//	private static void checkBounds(byte[] bytes, int offset, int length) {
//		if (length < 0)
//			throw new StringIndexOutOfBoundsException(length);
//		if (offset < 0)
//			throw new StringIndexOutOfBoundsException(offset);
//		if (offset > bytes.length - length)
//			throw new StringIndexOutOfBoundsException(offset + length);
//	}
//
//	public String(byte bytes[], int offset, int length, String charsetName)
//			throws UnsupportedEncodingException {
//		if (charsetName == null)
//			throw new NullPointerException("charsetName");
//		checkBounds(bytes, offset, length);
//		this.value = StringCoding.decode(charsetName, bytes, offset, length);
//	}
//
//	public String(byte bytes[], int offset, int length, Charset charset) {
//		if (charset == null)
//			throw new NullPointerException("charset");
//		checkBounds(bytes, offset, length);
//		this.value =  StringCoding.decode(charset, bytes, offset, length);
//	}
//
//	public String(byte bytes[], String charsetName)
//			throws UnsupportedEncodingException {
//		this(bytes, 0, bytes.length, charsetName);
//	}
/**
 * 所谓好的适用性模块，一定是能有一坨坨的各种适应代码的。
 * 下面是一系列的利用byte[]数组来构建String对象的构造器，主要差别是可能需要指定特殊的字符集来解码，
 * 但是这一点其实在web编程，网络编程中还是很重要的。
 */
//	public String(byte bytes[], Charset charset) {
//		this(bytes, 0, bytes.length, charset);
//	}
//	public String(byte bytes[], int offset, int length) {
//		checkBounds(bytes, offset, length);
//		this.value = StringCoding.decode(bytes, offset, length);
//	}
//	public String(byte bytes[]) {
//		this(bytes, 0, bytes.length);
//	}
/**
 * (5)基于StringBuilder,StringBuffer参数
 */
//	public String(StringBuffer buffer) {
//		synchronized(buffer) { //由于不是原子性操作，仍然使用了同步方法synchronized
//			this.value = Arrays.copyOf(buffer.getValue(), buffer.length());
//		}
//	}
//	public String(StringBuilder builder) {
//		this.value = Arrays.copyOf(builder.getValue(), builder.length());
//	}
//
//	String(char[] value, boolean share) {
//		this.value = value;
//	}
//返回字符串中所包含的字符数目，即value数组的长度
//	public int length() {
//		return value.length;
//	}
//判断字符串是否为空，即判断value数组的长度为0即可
//	public boolean isEmpty() {
//		return value.length == 0;
//	}
//返回第index个字符，即只需要检索value数组即可
//	public char charAt(int index) {
//		if ((index < 0) || (index >= value.length)) {
//			throw new StringIndexOutOfBoundsException(index);
//		}
//		return value[index];
//	}
//
//	public int codePointAt(int index) {
//		if ((index < 0) || (index >= value.length)) {
//			throw new StringIndexOutOfBoundsException(index);
//		}
//		return Character.codePointAtImpl(value, index, value.length);
//	}
//
//	public int codePointBefore(int index) {
//		int i = index - 1;
//		if ((i < 0) || (i >= value.length)) {
//			throw new StringIndexOutOfBoundsException(index);
//		}
//		return Character.codePointBeforeImpl(value, index, 0);
//	}
//
//	public int codePointCount(int beginIndex, int endIndex) {
//		if (beginIndex < 0 || endIndex > value.length || beginIndex > endIndex) {
//			throw new IndexOutOfBoundsException();
//		}
//		return Character.codePointCountImpl(value, beginIndex, endIndex - beginIndex);
//	}
//
//	public int offsetByCodePoints(int index, int codePointOffset) {
//		if (index < 0 || index > value.length) {
//			throw new IndexOutOfBoundsException();
//		}
//		return Character.offsetByCodePointsImpl(value, 0, value.length,
//				index, codePointOffset);
//	}
//
//	void getChars(char dst[], int dstBegin) {
//		System.arraycopy(value, 0, dst, dstBegin, value.length);
//	}
//
//	public void getChars(int srcBegin, int srcEnd, char dst[], int dstBegin) {
//		if (srcBegin < 0) {
//			throw new StringIndexOutOfBoundsException(srcBegin);
//		}
//		if (srcEnd > value.length) {
//			throw new StringIndexOutOfBoundsException(srcEnd);
//		}
//		if (srcBegin > srcEnd) {
//			throw new StringIndexOutOfBoundsException(srcEnd - srcBegin);
//		}
//		System.arraycopy(value, srcBegin, dst, dstBegin, srcEnd - srcBegin);
//	}
//
//	@Deprecated
//	public void getBytes(int srcBegin, int srcEnd, byte dst[], int dstBegin) {
//		if (srcBegin < 0) {
//			throw new StringIndexOutOfBoundsException(srcBegin);
//		}
//		if (srcEnd > value.length) {
//			throw new StringIndexOutOfBoundsException(srcEnd);
//		}
//		if (srcBegin > srcEnd) {
//			throw new StringIndexOutOfBoundsException(srcEnd - srcBegin);
//		}
//		Objects.requireNonNull(dst);
//
//		int j = dstBegin;
//		int n = srcEnd;
//		int i = srcBegin;
//		char[] val = value;   /* avoid getfield opcode */
//
//		while (i < n) {
//			dst[j++] = (byte)val[i++];
//		}
//	}
//
//	public byte[] getBytes(String charsetName)
//			throws UnsupportedEncodingException {
//		if (charsetName == null) throw new NullPointerException();
//		return StringCoding.encode(charsetName, value, 0, value.length);
//	}
//
//	public byte[] getBytes(Charset charset) {
//		if (charset == null) throw new NullPointerException();
//		return StringCoding.encode(charset, value, 0, value.length);
//	}
//String对象转为byte数组
//	public byte[] getBytes() {
//		return StringCoding.encode(value, 0, value.length);
//	}
//可以看到equals方法重写了，会判断两个字符串的每一个字符是否相等。
//	public boolean equals(Object anObject) {
//		if (this == anObject) {
//			return true;
//		}
//		if (anObject instanceof String) {
//			String anotherString = (String)anObject;
//			int n = value.length;
//			if (n == anotherString.value.length) {
//				char v1[] = value;
//				char v2[] = anotherString.value;
//				int i = 0;
//				while (n-- != 0) {
//					if (v1[i] != v2[i])
//						return false;
//					i++;
//				}
//				return true;
//			}
//		}
//		return false;
//	}
//
//	public boolean contentEquals(StringBuffer sb) {
//		return contentEquals((CharSequence)sb);
//	}
//
//	private boolean nonSyncContentEquals(AbstractStringBuilder sb) {
//		char v1[] = value;
//		char v2[] = sb.getValue();
//		int n = v1.length;
//		if (n != sb.length()) {
//			return false;
//		}
//		for (int i = 0; i < n; i++) {
//			if (v1[i] != v2[i]) {
//				return false;
//			}
//		}
//		return true;
//	}
//
//	public boolean contentEquals(CharSequence cs) {
//		// Argument is a StringBuffer, StringBuilder
//		if (cs instanceof AbstractStringBuilder) {
//			if (cs instanceof StringBuffer) {
//				synchronized(cs) {
//					return nonSyncContentEquals((AbstractStringBuilder)cs);
//				}
//			} else {
//				return nonSyncContentEquals((AbstractStringBuilder)cs);
//			}
//		}
//		// Argument is a String
//		if (cs instanceof String) {
//			return equals(cs);
//		}
//		// Argument is a generic CharSequence
//		char v1[] = value;
//		int n = v1.length;
//		if (n != cs.length()) {
//			return false;
//		}
//		for (int i = 0; i < n; i++) {
//			if (v1[i] != cs.charAt(i)) {
//				return false;
//			}
//		}
//		return true;
//	}
//判断两个字符串在忽略大小写的情况下是否相等，主要调用regionMatches方法
//	public boolean equalsIgnoreCase(String anotherString) {
//		return (this == anotherString) ? true
//				: (anotherString != null)
//				&& (anotherString.value.length == value.length)
//				&& regionMatches(true, 0, anotherString, 0, value.length);
//	}
/**
 * 比较两个字符串的大小。如果两个字符串的字符序列相等，则返回0；不相等时，从两个字符串第0个字符开始比较，返回第一个不相等的字符差。
 * 另一种情况，较长的字符串的前面部分恰好是较短的字符串，则返回他们的长度差。
 */
//	public int compareTo(String anotherString) {
//		int len1 = value.length;
//		int len2 = anotherString.value.length;
//		int lim = Math.min(len1, len2);
//		char v1[] = value;
//		char v2[] = anotherString.value;
//		int k = 0;
//		while (k < lim) {
//			char c1 = v1[k];
//			char c2 = v2[k];
//			if (c1 != c2) {
//				return c1 - c2;
//			}
//			k++;
//		}
//		return len1 - len2;
//	}
//
//	public static final Comparator<String> CASE_INSENSITIVE_ORDER
//			= new CaseInsensitiveComparator();
//	private static class CaseInsensitiveComparator
//			implements Comparator<String>, java.io.Serializable {
//		// use serialVersionUID from JDK 1.2.2 for interoperability
//		private static final long serialVersionUID = 8575799808933029326L;
//
//		public int compare(String s1, String s2) {
//			int n1 = s1.length();
//			int n2 = s2.length();
//			int min = Math.min(n1, n2);
//			for (int i = 0; i < min; i++) {
//				char c1 = s1.charAt(i);
//				char c2 = s2.charAt(i);
//				if (c1 != c2) {
//					c1 = Character.toUpperCase(c1);
//					c2 = Character.toUpperCase(c2);
//					if (c1 != c2) {
//						c1 = Character.toLowerCase(c1);
//						c2 = Character.toLowerCase(c2);
//						if (c1 != c2) {
//							// No overflow because of numeric promotion
//							return c1 - c2;
//						}
//					}
//				}
//			}
//			return n1 - n2;
//		}
//
//		/** Replaces the de-serialized object. */
//		private Object readResolve() { return CASE_INSENSITIVE_ORDER; }
//	}
//
//	public int compareToIgnoreCase(String str) {
//		return CASE_INSENSITIVE_ORDER.compare(this, str);
//	}
//判断部分子字符串是否相等，主要用来判断一段区间内是否相等。
//	public boolean regionMatches(int toffset, String other, int ooffset,
//								 int len) {
//		char ta[] = value;
//		int to = toffset;
//		char pa[] = other.value;
//		int po = ooffset;
//		if ((ooffset < 0) || (toffset < 0)
//				|| (toffset > (long)value.length - len)
//				|| (ooffset > (long)other.value.length - len)) {
//			return false;
//		}
//		while (len-- > 0) {
//			if (ta[to++] != pa[po++]) {
//				return false;
//			}
//		}
//		return true;
//	}
//
//	public boolean regionMatches(boolean ignoreCase, int toffset,
//								 String other, int ooffset, int len) {
//		char ta[] = value;
//		int to = toffset;
//		char pa[] = other.value;
//		int po = ooffset;
//		// Note: toffset, ooffset, or len might be near -1>>>1.
//		if ((ooffset < 0) || (toffset < 0)
//				|| (toffset > (long)value.length - len)
//				|| (ooffset > (long)other.value.length - len)) {
//			return false;
//		}
//		while (len-- > 0) {
//			char c1 = ta[to++];
//			char c2 = pa[po++];
//			if (c1 == c2) { //在这里先行判断，如果相等就直接跳过后面即可，可以提高效率
//				continue;
//			}
//			if (ignoreCase) {
//				// If characters don't match but case may be ignored,
//				// try converting both characters to uppercase.
//				// If the results match, then the comparison scan should
//				// continue.
//				char u1 = Character.toUpperCase(c1);
//				char u2 = Character.toUpperCase(c2);
//				if (u1 == u2) { //都转换成大写的形式，如果相等，则跳过
//					continue;
//				}
//				if (Character.toLowerCase(u1) == Character.toLowerCase(u2)) {
//					continue;
//				}
//			}
//			return false;
//		}
//		return true;
//	}
//该对象从offset位置算起，是否以prefix开始。
//	public boolean startsWith(String prefix, int toffset) {
//		char ta[] = value;
//		int to = toffset;
//		char pa[] = prefix.value;
//		int po = 0;
//		int pc = prefix.value.length;
//		if ((toffset < 0) || (toffset > value.length - pc)) {
//			return false;
//		}
//		while (--pc >= 0) {
//			if (ta[to++] != pa[po++]) {
//				return false;
//			}
//		}
//		return true;
//	}
//判断String是否以prefix字符串开始。
//	public boolean startsWith(String prefix) {
//		return startsWith(prefix, 0);
//	}
//判断String是否以suffix结尾，可以看到其直接复用了startsWith。
//	public boolean endsWith(String suffix) {
//		return startsWith(suffix, value.length - suffix.value.length);
//	}
//
//	public int hashCode() {
//		int h = hash;
//		if (h == 0 && value.length > 0) {
//			char val[] = value;
//
//			for (int i = 0; i < value.length; i++) {
//				h = 31 * h + val[i];
//			}
//			hash = h;
//		}
//		return h;
//	}
//通过调用indexOf(int ch,int fromIndex)来实现
//	public int indexOf(int ch) {
//		return indexOf(ch, 0);
//	}
/**
 * 找出ch字符在该字符串中从fromIndex开始后第一次出现的位置
 * 说明：而我们应用这个方法时却只是这样应用 String s="abcdefg";
 	int idx=s.indexOf('f');//idx=5
 可见我们并没有直接传入一个int型的参数，而是直接传入char型
 这里其实涉及到了自动类型转换中的，自动提升问题，
 当把一个表数范围小的数值或变量直接赋给另一个表数范围大的变量时，系统将可以进行自动类型转换。也就是这里char类型自动转为了int型。
 */
//	public int indexOf(int ch, int fromIndex) {
//		final int max = value.length;
//		if (fromIndex < 0) {
//			fromIndex = 0;
//		} else if (fromIndex >= max) {
//			return -1;
//		}
//
//		if (ch < Character.MIN_SUPPLEMENTARY_CODE_POINT) {
//			final char[] value = this.value;
//			for (int i = fromIndex; i < max; i++) {
//				if (value[i] == ch) {
//					return i;
//				}
//			}
//			return -1;
//		} else {
//			return indexOfSupplementary(ch, fromIndex);
//		}
//	}
//
//	private int indexOfSupplementary(int ch, int fromIndex) {
//		if (Character.isValidCodePoint(ch)) {
//			final char[] value = this.value;
//			final char hi = Character.highSurrogate(ch);
//			final char lo = Character.lowSurrogate(ch);
//			final int max = value.length - 1;
//			for (int i = fromIndex; i < max; i++) {
//				if (value[i] == hi && value[i + 1] == lo) {
//					return i;
//				}
//			}
//		}
//		return -1;
//	}
//找出ch字符在该字符串中最后一次出现的位置
//	public int lastIndexOf(int ch) {
//		return lastIndexOf(ch, value.length - 1);
//	}
//返回值：在此对象表示的字符序列（小于等于fromIndex）中最后一次出现该字符的索引；如果在该点之前未出现该字符，则返回-1。
//	public int lastIndexOf(int ch, int fromIndex) {
//		if (ch < Character.MIN_SUPPLEMENTARY_CODE_POINT) {
//			final char[] value = this.value;
//			int i = Math.min(fromIndex, value.length - 1);
//			for (; i >= 0; i--) {
//				if (value[i] == ch) {
//					return i;
//				}
//			}
//			return -1;
//		} else {
//			return lastIndexOfSupplementary(ch, fromIndex);
//		}
//	}
//
//	private int lastIndexOfSupplementary(int ch, int fromIndex) {
//		if (Character.isValidCodePoint(ch)) {
//			final char[] value = this.value;
//			char hi = Character.highSurrogate(ch);
//			char lo = Character.lowSurrogate(ch);
//			int i = Math.min(fromIndex, value.length - 2);
//			for (; i >= 0; i--) {
//				if (value[i] == hi && value[i + 1] == lo) {
//					return i;
//				}
//			}
//		}
//		return -1;
//	}
//找出str子字符串在该字符串中第一次出现的位置。
//	public int indexOf(String str) {
//		return indexOf(str, 0);
//	}
//	public int indexOf(String str, int fromIndex) {
//		return indexOf(value, 0, value.length,
//				str.value, 0, str.value.length, fromIndex);
//	}
//	static int indexOf(char[] source, int sourceOffset, int sourceCount,
//					   String target, int fromIndex) {
//		return indexOf(source, sourceOffset, sourceCount,
//				target.value, 0, target.value.length,
//				fromIndex);
//	}
/**
 *	最终调用的代码，为下面的代码，这里可能有点乱，但是只要理清楚这几个参数即可理清楚整个过程了。
 */
/* @param   source       the characters being searched.//这里就是value数组
 * @param   sourceOffset offset of the source string./ //源字符串的偏移量
 * @param   sourceCount  count of the source string.    //这里是value数组的长度
 * @param   target       the characters being searched for.  //待搜索目标字符串
 * @param   targetOffset offset of the target string.   //待搜索目标字符串的偏移量
 * @param   targetCount  count of the target string.   //待搜索目标字符串的长度
 * @param   fromIndex    the index to begin searching from. //起始位置
 */
//	static int indexOf(char[] source, int sourceOffset, int sourceCount,
//					   char[] target, int targetOffset, int targetCount,
//					   int fromIndex) {
//		if (fromIndex >= sourceCount) { //越界了
//			return (targetCount == 0 ? sourceCount : -1);
//		}
//		if (fromIndex < 0) {
//			fromIndex = 0;
//		}
//		if (targetCount == 0) {
//			return fromIndex;
//		}
//
//		char first = target[targetOffset]; //待搜索字符串第一个字符
//		int max = sourceOffset + (sourceCount - targetCount);//搜索第一个匹配的字符时所能达到的最大值，因为要保证后面的长度>=targetCount
//
//		下面这里就是核心搜索算法了，会先匹配第一个字符，然后依次向后移，直到完全匹配
//		或者是匹配到max仍然没有匹配成功
//		for (int i = sourceOffset + fromIndex; i <= max; i++) {
//			if (source[i] != first) {
//				while (++i <= max && source[i] != first);
//			}
//			可以注意这里i下标只是用来匹配第一个字符，因为有可能部分匹配时，需要从先在匹配
//			所以这里重新应用下标j
//			if (i <= max) {
//				int j = i + 1;
//				int end = j + targetCount - 1;
//				for (int k = targetOffset + 1; j < end && source[j]== target[k]; j++, k++);
//				if (j == end) {
//					return i - sourceOffset;
//				}
//			}
//		}
//		return -1;//当匹配失败时，返回-1
//	}
/**
 * 这段搜索匹配的代码写的非常漂亮，代码简洁而且清晰。感觉哪怕分析String源码看到这一段也值了。
 * */
//
//	public int lastIndexOf(String str) {
//		return lastIndexOf(str, value.length);//这里fromIndex传入的是value数组的长度，因为要进行的是倒序匹配，表明从最后一个字符开始
//	}
//  要搜索返回的字符串下标要小于等于fromIndex,然后再是其中的最大值, 也就是正向起始搜索位置最大值为fromIndex,fromIndex为开始反向搜索的索引位置
//	public int lastIndexOf(String str, int fromIndex) {
//		return lastIndexOf(value, 0, value.length,
//				str.value, 0, str.value.length, fromIndex);
//	}
//	static int lastIndexOf(char[] source, int sourceOffset, int sourceCount,
//						   String target, int fromIndex) {
//		return lastIndexOf(source, sourceOffset, sourceCount,
//				target.value, 0, target.value.length,
//				fromIndex);
//	}
/**
 * 最终调用的方法如下，与上面的方法类似，只不过这次是从后搜索，所以匹配也倒着匹配从最后一个字符匹配。
 * */
//	static int lastIndexOf(char[] source, int sourceOffset, int sourceCount,
//						   char[] target, int targetOffset, int targetCount,
//						   int fromIndex) {
//		int rightIndex = sourceCount - targetCount;//第一个字符所能匹配的最大位置，类似于上面的max
//		if (fromIndex < 0) {
//			return -1;
//		}
//		if (fromIndex > rightIndex) {
//			fromIndex = rightIndex;
//		}
//        /* Empty string always matches. */
//		if (targetCount == 0) {
//			return fromIndex;
//		}
//
//		int strLastIndex = targetOffset + targetCount - 1;//目标字符串最后一个字符下标
//		char strLastChar = target[strLastIndex];//最后一个字符
//		int min = sourceOffset + targetCount - 1;//目标字符串最后一个字符所能匹配的源字符串最小下标
//		int i = min + fromIndex;//这里i下标永远是最后一个字符匹配的下标索引
//
//		startSearchForLastChar:
//		while (true) {
//			while (i >= min && source[i] != strLastChar) {
//				i--;
//			}
//			if (i < min) {//小于min则不可能在搜索到了
//				return -1;
//			}
//			int j = i - 1;
//			int start = j - (targetCount - 1);
//			int k = strLastIndex - 1;
//
//			while (j > start) {
//				if (source[j--] != target[k--]) {
//					当存在部分匹配，而前半部分不匹配时，跳出当前查找，整体向前窗移
//					i--;
//					continue startSearchForLastChar;//直接跳到顶层while循环
//				}
//			}
//			return start - sourceOffset + 1;
//		}
//	}
//这里要注意的是这个方法是substring，而不是subString;
//获取从beginIndex开始到结束的子字符串，而这里返回一个新建的String对象.
//	public String substring(int beginIndex) {
//		if (beginIndex < 0) {
//			throw new StringIndexOutOfBoundsException(beginIndex);
//		}
//		int subLen = value.length - beginIndex;
//		if (subLen < 0) {
//			throw new StringIndexOutOfBoundsException(subLen);
//		}
//		return (beginIndex == 0) ? this : new String(value, beginIndex, subLen);
//	}
//获取从beginIndex位置开始到endIndex位置的子字符串，但是这里不包含endIndex，因为长度为endIndex-beginIndex;
//	public String substring(int beginIndex, int endIndex) {
//		if (beginIndex < 0) {
//			throw new StringIndexOutOfBoundsException(beginIndex);
//		}
//		if (endIndex > value.length) {
//			throw new StringIndexOutOfBoundsException(endIndex);
//		}
//		int subLen = endIndex - beginIndex;
//		if (subLen < 0) {
//			throw new StringIndexOutOfBoundsException(subLen);
//		}
//		return ((beginIndex == 0) && (endIndex == value.length)) ? this
//				: new String(value, beginIndex, subLen);
//	}
//
//	public CharSequence subSequence(int beginIndex, int endIndex) {
//		return this.substring(beginIndex, endIndex);
//	}
/**
 * 将该String对象与str连接在一起，与+运算符功能相同,但是可以看到已经新new一个String对象了，所以对于String对象慎用==，一定要用equals()
 这个方法主要调用了getChars(buf,len)方法，而getChars方法只是一个数组复制包装方法;
 */
//	public String concat(String str) {
//		int otherLen = str.length();
//		if (otherLen == 0) {
//			return this;
//		}
//		int len = value.length;
//		char buf[] = Arrays.copyOf(value, len + otherLen);
//		str.getChars(buf, len);
//		return new String(buf, true);
//	}
/**
 * 将字符串中的所有oldChar替换为newChar.
 * 这里有个疑问？为什么不直接一遍复制然后直接替换呢？为啥还需要在先扫描一遍，查找是否存在oldChar呢？
 * 可能也是为了节省重新建String对象的内存吧，当不存在oldChar时，只要返回当前String对象即可，而不需要重新再建一个String对象了，
 * 而由于String是不可变的对象，所以即便是后来的引用做任何改变也不会影响原来的String对象。
 */
//	public String replace(char oldChar, char newChar) {
//		if (oldChar != newChar) {
//			int len = value.length;
//			int i = -1;
//			char[] val = value;
//
//			while (++i < len) {
//				if (val[i] == oldChar) {
//					break;
//				}
//			}
//			这里也可以发现由于String是不可变的，所以当改变其中某一个值时，只能在建一个String对象
//			而再建对象就涉及到了重新复制的处理，比较麻烦
//			if (i < len) {
//				char buf[] = new char[len];
//				for (int j = 0; j < i; j++) {
//					buf[j] = val[j];
//				}
//				while (i < len) {
//					char c = val[i];
//					buf[i] = (c == oldChar) ? newChar : c;
//					i++;
//				}
//				return new String(buf, true);
//			}
//		}
//		return this;
//	}
//判断字符串是否完全匹配该正则表达式
//	public boolean matches(String regex) {
//		return Pattern.matches(regex, this);
//	}
//
//	public boolean contains(CharSequence s) {
//		return indexOf(s.toString()) > -1;
//	}
//将第一个匹配的字符串替换成replacement字符串，并且返回新的字符串
//	public String replaceFirst(String regex, String replacement) {
//		return Pattern.compile(regex).matcher(this).replaceFirst(replacement);
//	}
//将所有匹配的字符串替换成replacement字符串，并且返回新的字符串
//	public String replaceAll(String regex, String replacement) {
//		return Pattern.compile(regex).matcher(this).replaceAll(replacement);
//	}
//
//	public String replace(CharSequence target, CharSequence replacement) {
//		return Pattern.compile(target.toString(), Pattern.LITERAL).matcher(
//				this).replaceAll(Matcher.quoteReplacement(replacement.toString()));
//	}
//
//	public String[] split(String regex, int limit) {
//        /* fastpath if the regex is a
//         (1)one-char String and this character is not one of the
//            RegEx's meta characters ".$|()[{^?*+\\", or
//         (2)two-char String and the first char is the backslash and
//            the second is not the ascii digit or ascii letter.
//         */
//		char ch = 0;
//		if (((regex.value.length == 1 &&
//				".$|()[{^?*+\\".indexOf(ch = regex.charAt(0)) == -1) ||
//				(regex.length() == 2 &&
//						regex.charAt(0) == '\\' &&
//						(((ch = regex.charAt(1))-'0')|('9'-ch)) < 0 &&
//						((ch-'a')|('z'-ch)) < 0 &&
//						((ch-'A')|('Z'-ch)) < 0)) &&
//				(ch < Character.MIN_HIGH_SURROGATE ||
//						ch > Character.MAX_LOW_SURROGATE))
//		{
//			int off = 0;
//			int next = 0;
//			boolean limited = limit > 0;
//			ArrayList<String> list = new ArrayList<>();
//			while ((next = indexOf(ch, off)) != -1) {
//				if (!limited || list.size() < limit - 1) {
//					list.add(substring(off, next));
//					off = next + 1;
//				} else {    // last one
//					//assert (list.size() == limit - 1);
//					list.add(substring(off, value.length));
//					off = value.length;
//					break;
//				}
//			}
//			// If no match was found, return this
//			if (off == 0)
//				return new String[]{this};
//
//			// Add remaining segment
//			if (!limited || list.size() < limit)
//				list.add(substring(off, value.length));
//
//			// Construct result
//			int resultSize = list.size();
//			if (limit == 0) {
//				while (resultSize > 0 && list.get(resultSize - 1).length() == 0) {
//					resultSize--;
//				}
//			}
//			String[] result = new String[resultSize];
//			return list.subList(0, resultSize).toArray(result);
//		}
//		return Pattern.compile(regex).split(this, limit);
//	}
//将字符串按照指定表达式进行分割，分别返回分割后的String数组
//	public String[] split(String regex) {
//		return split(regex, 0);
//	}
//
//	public static String join(CharSequence delimiter, CharSequence... elements) {
//		Objects.requireNonNull(delimiter);
//		Objects.requireNonNull(elements);
//		// Number of elements not likely worth Arrays.stream overhead.
//		StringJoiner joiner = new StringJoiner(delimiter);
//		for (CharSequence cs: elements) {
//			joiner.add(cs);
//		}
//		return joiner.toString();
//	}
//
//	public static String join(CharSequence delimiter,
//							  Iterable<? extends CharSequence> elements) {
//		Objects.requireNonNull(delimiter);
//		Objects.requireNonNull(elements);
//		StringJoiner joiner = new StringJoiner(delimiter);
//		for (CharSequence cs: elements) {
//			joiner.add(cs);
//		}
//		return joiner.toString();
//	}
//
//	public String toLowerCase(Locale locale) {
//		if (locale == null) {
//			throw new NullPointerException();
//		}
//
//		int firstUpper;
//		final int len = value.length;
//
//		scan: {
//			for (firstUpper = 0 ; firstUpper < len; ) {
//				char c = value[firstUpper];
//				if ((c >= Character.MIN_HIGH_SURROGATE)
//						&& (c <= Character.MAX_HIGH_SURROGATE)) {
//					int supplChar = codePointAt(firstUpper);
//					if (supplChar != Character.toLowerCase(supplChar)) {
//						break scan;
//					}
//					firstUpper += Character.charCount(supplChar);
//				} else {
//					if (c != Character.toLowerCase(c)) {
//						break scan;
//					}
//					firstUpper++;
//				}
//			}
//			return this;
//		}
//
//		char[] result = new char[len];
//		int resultOffset = 0;  /* result may grow, so i+resultOffset
//                                * is the write location in result */
//
//		System.arraycopy(value, 0, result, 0, firstUpper);
//
//		String lang = locale.getLanguage();
//		boolean localeDependent =
//				(lang == "tr" || lang == "az" || lang == "lt");
//		char[] lowerCharArray;
//		int lowerChar;
//		int srcChar;
//		int srcCount;
//		for (int i = firstUpper; i < len; i += srcCount) {
//			srcChar = (int)value[i];
//			if ((char)srcChar >= Character.MIN_HIGH_SURROGATE
//					&& (char)srcChar <= Character.MAX_HIGH_SURROGATE) {
//				srcChar = codePointAt(i);
//				srcCount = Character.charCount(srcChar);
//			} else {
//				srcCount = 1;
//			}
//			if (localeDependent ||
//					srcChar == '\u03A3' || // GREEK CAPITAL LETTER SIGMA
//					srcChar == '\u0130') { // LATIN CAPITAL LETTER I WITH DOT ABOVE
//				lowerChar = ConditionalSpecialCasing.toLowerCaseEx(this, i, locale);
//			} else {
//				lowerChar = Character.toLowerCase(srcChar);
//			}
//			if ((lowerChar == Character.ERROR)
//					|| (lowerChar >= Character.MIN_SUPPLEMENTARY_CODE_POINT)) {
//				if (lowerChar == Character.ERROR) {
//					lowerCharArray =
//							ConditionalSpecialCasing.toLowerCaseCharArray(this, i, locale);
//				} else if (srcCount == 2) {
//					resultOffset += Character.toChars(lowerChar, result, i + resultOffset) - srcCount;
//					continue;
//				} else {
//					lowerCharArray = Character.toChars(lowerChar);
//				}
//
//                /* Grow result if needed */
//				int mapLen = lowerCharArray.length;
//				if (mapLen > srcCount) {
//					char[] result2 = new char[result.length + mapLen - srcCount];
//					System.arraycopy(result, 0, result2, 0, i + resultOffset);
//					result = result2;
//				}
//				for (int x = 0; x < mapLen; ++x) {
//					result[i + resultOffset + x] = lowerCharArray[x];
//				}
//				resultOffset += (mapLen - srcCount);
//			} else {
//				result[i + resultOffset] = (char)lowerChar;
//			}
//		}
//		return new String(result, 0, len + resultOffset);
//	}
//
//	public String toLowerCase() {
//		return toLowerCase(Locale.getDefault());
//	}
//
//	public String toUpperCase(Locale locale) {
//		if (locale == null) {
//			throw new NullPointerException();
//		}
//
//		int firstLower;
//		final int len = value.length;
//
//		scan: {
//			for (firstLower = 0 ; firstLower < len; ) {
//				int c = (int)value[firstLower];
//				int srcCount;
//				if ((c >= Character.MIN_HIGH_SURROGATE)
//						&& (c <= Character.MAX_HIGH_SURROGATE)) {
//					c = codePointAt(firstLower);
//					srcCount = Character.charCount(c);
//				} else {
//					srcCount = 1;
//				}
//				int upperCaseChar = Character.toUpperCaseEx(c);
//				if ((upperCaseChar == Character.ERROR)
//						|| (c != upperCaseChar)) {
//					break scan;
//				}
//				firstLower += srcCount;
//			}
//			return this;
//		}
//
//		int resultOffset = 0;
//		char[] result = new char[len]; /* may grow */
//
//		System.arraycopy(value, 0, result, 0, firstLower);
//
//		String lang = locale.getLanguage();
//		boolean localeDependent =
//				(lang == "tr" || lang == "az" || lang == "lt");
//		char[] upperCharArray;
//		int upperChar;
//		int srcChar;
//		int srcCount;
//		for (int i = firstLower; i < len; i += srcCount) {
//			srcChar = (int)value[i];
//			if ((char)srcChar >= Character.MIN_HIGH_SURROGATE &&
//					(char)srcChar <= Character.MAX_HIGH_SURROGATE) {
//				srcChar = codePointAt(i);
//				srcCount = Character.charCount(srcChar);
//			} else {
//				srcCount = 1;
//			}
//			if (localeDependent) {
//				upperChar = ConditionalSpecialCasing.toUpperCaseEx(this, i, locale);
//			} else {
//				upperChar = Character.toUpperCaseEx(srcChar);
//			}
//			if ((upperChar == Character.ERROR)
//					|| (upperChar >= Character.MIN_SUPPLEMENTARY_CODE_POINT)) {
//				if (upperChar == Character.ERROR) {
//					if (localeDependent) {
//						upperCharArray =
//								ConditionalSpecialCasing.toUpperCaseCharArray(this, i, locale);
//					} else {
//						upperCharArray = Character.toUpperCaseCharArray(srcChar);
//					}
//				} else if (srcCount == 2) {
//					resultOffset += Character.toChars(upperChar, result, i + resultOffset) - srcCount;
//					continue;
//				} else {
//					upperCharArray = Character.toChars(upperChar);
//				}
//
//                /* Grow result if needed */
//				int mapLen = upperCharArray.length;
//				if (mapLen > srcCount) {
//					char[] result2 = new char[result.length + mapLen - srcCount];
//					System.arraycopy(result, 0, result2, 0, i + resultOffset);
//					result = result2;
//				}
//				for (int x = 0; x < mapLen; ++x) {
//					result[i + resultOffset + x] = upperCharArray[x];
//				}
//				resultOffset += (mapLen - srcCount);
//			} else {
//				result[i + resultOffset] = (char)upperChar;
//			}
//		}
//		return new String(result, 0, len + resultOffset);
//	}
//
//	public String toUpperCase() {
//		return toUpperCase(Locale.getDefault());
//	}
/**
 * 这个trim()是去掉首尾的空格，而实现方式也非常简单，分别找到第一个非空格字符的下标，与最后一个非空格字符的下标
 然后返回之间的子字符串。注意这里由于应用了substring方法，所以len变量的控制要小心
 */
//	public String trim() {
//		int len = value.length;
//		int st = 0;
//		char[] val = value;
//		while ((st < len) && (val[st] <= ' ')) {
//			st++;
//		}
//		while ((st < len) && (val[len - 1] <= ' ')) {
//			len--;
//		}
//		return ((st > 0) || (len < value.length)) ? substring(st, len) : this;
//	}
//
//	public String toString() {
//		return this;
//	}
//返回char[]数组，相等于重新拷贝一份然后返回
//	public char[] toCharArray() {
//		char result[] = new char[value.length];
//		System.arraycopy(value, 0, result, 0, value.length);
//		return result;
//	}
//
//	public static String format(String format, Object... args) {
//		return new Formatter().format(format, args).toString();
//	}
//
//	public static String format(Locale l, String format, Object... args) {
//		return new Formatter(l).format(format, args).toString();
//	}
//
//	public static String valueOf(Object obj) {
//		return (obj == null) ? "null" : obj.toString();
//	}
//
//	public static String valueOf(char data[]) {
//		return new String(data);
//	}
//
//	public static String valueOf(char data[], int offset, int count) {
//		return new String(data, offset, count);
//	}
//
//	public static String copyValueOf(char data[], int offset, int count) {
//		return new String(data, offset, count);
//	}
//
//	public static String copyValueOf(char data[]) {
//		return new String(data);
//	}
//
//	public static String valueOf(boolean b) {
//		return b ? "true" : "false";
//	}
//
//	public static String valueOf(char c) {
//		char data[] = {c};
//		return new String(data, true);
//	}
//
//	public static String valueOf(int i) {
//		return Integer.toString(i);
//	}
//
//	public static String valueOf(long l) {
//		return Long.toString(l);
//	}
//
//	public static String valueOf(float f) {
//		return Float.toString(f);
//	}
//
//	public static String valueOf(double d) {
//		return Double.toString(d);
//	}
//
//	public native String intern();
