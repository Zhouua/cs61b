* The Process and Notes of Learning CS61B Spring 2021


** 1.17 & 1.18 finish Reading 1.1

Use of command line：
https://sp18.datastructur.es/materials/lab/lab1setup/lab1setup.html
（touch 61b.txt to create txt）

Use of git and the remote repo：
https://sp18.datastructur.es/materials/guides/using-git.html

Enhanced “for”:
for(Type s : Array){}
(e.g. for(String name : nameArray){System.out.prinln(name);})


** 1.19 & 1.21 finish Reading 1.2

Difference between “public static void xxx(){}” and “public void xxx(){}”：
when use “static”, must use the class name to call it


** 1.22 & 1.23 finish Project 0

Model.java was divided into four parts: 
emptySpaceExists, maxTileExists, atLeastOneMoveExists, tilt

Avoid “magic number”


** 1.23 finish Reading 1.3

Use junit to test：
import org.junit.Test;
import static org.junit.Assert.*;

assertArrayEquals(expect, actual);
if no “import static org.junit.Assert.*”
org.junit.Assert.assertArrayEquals(expected, actual);
/* to compare array expect and actual */

/* Other functions: https://junit.org/junit4/javadoc/4.12/org/junit/Assert.html 
* before every test method, annotate with @Test
* all test methods are non-static
* run without main method */



Strings comparison:
str1.equals(str2)
do not use “==” ,this compare the points
/* Java has a string pool which lay existed string,
* when String str1 = “abc”, String str2 = “abc”,their points are the same; 
* but when String str1 = new String(abc), String str2 = new String(abc), their points are not the same */
str1.compareTo(str3)	return 0 means str1 equals str2; return 1 means str1 comes after str3; return -1 means str3 ….


** 1.24 finish Reading 2.1

Import library and try debugging


** 1.25 finish Reading 2.2

Use SLList(i.e. Singly Linked List) to avoid the naked recursion:
/* Naked  recursion:
* IntList L = new IntList(5, null);
* L = new IntList(2, L);
* the latter point is confusing! 
* and SLList can easily create empty list/




Important! In this combination, we can use L.size() to get the size through recursion.

Private variables and methods can only be accessed by code inside the same class

Java allows nested classes
/* When use static to declare the nested class, it means that
* methods inside the static class can not access
* any of the members of the enclosing class. 
* This saves a bit of memory */

Creates empty list:
public SSList(){first = null;}		but this has problem when call addLast() function because null.next is invalid!!!!
/* To solve this:
* one way is to change the addLast() function considering the null condition.(It’s ugly)
* another is to use Sentinel Node.Lay sentinel node in the “0th place”,thus using sentinel.next to replace first.
* this makes all code in harmony and consistent.NOTICE:do not change sentinel */



** 1.26 finish Reading 2.3 & Reading 2.4 & half of Project 1A

Use previous pointer to delete the last fast(i.e. Doubly Linked List),which takes constant time
/* Like “Create empty list”, to avoid the special cases in methods:
* one way is to change the last into a sentinel node(double sentinel: sentinelFront and sentinelBack) 
* another way is to build circle */



“Generics” allows to create data structures that hold any reference type:
Definition: public class ClassName<ArbitraryName>{}	in the class when use the type, use ArbitraryName to replace it
Use: ClassName<String> haha = new ClassName<>(“hello!”);
/* It looks like define in C, all the ArbitraryName ultimately replaced by String(the angle brackets)
NOTICE:
	•	specify generic type name only once after the class name
	•	use Integer, Double, Character, Boolean, Long, Short, Byte, Float instead of the primitive forms

System.arraycopy(array1, start1, array2, start2, copylen);
/* It’s faster than using loop to copy array, and results in more compact code.
* It is to copy array1’s copylen items form index start1 to array2(from index start2) */


** 1.27 finish Reading 2.5 & Project 1A

Resize arrays:
int[] items = new int[5];
size = items.length
int[] a = new int[size + 1];
System.arraycopy(items, 0, a, 0, size);
items = a;//Former array is useless, so just replace it.
size = size + 1;
/* Different from C, Java allows to use variant to initialize an array! */
/* This way is bad! It takes parabola time than linear time in SLList.
* To improve it, rather than add, we use multiply.
* int[] a = new int[size * x]; 
* when array is large, we need to downsize.
* Define “usage ratio” R = size / array.length
* Typically, when R < 1/4, resize. */

Generic arrays:
Different from class, Java does not allow to create an array of generic objects due to security.
So, we can’t do this: Haha[] items = new Haha[8]
Instead, we can only use this: Haha[] items = (Haha[]) new Object[8]

Remove last:
We should give the removed value “null”, so that the memory would free.
/* Java only destroys objects when the last reference has been lost. Avoid Wastage! */

Use circular array to avoid some certain cases

NOTICE: when initialize circular arrays, there is nothing between nextfirst and nextlast, which means nextfirst + 1 = nextlast.
 

** 1.30 finish Reading 4.1

Interface Inheritance and Implementation Inheritance:
Use a hypernym(superclass) to “replace” all the hyponyms(subclass). They both in an interface.
Define: public class AList<Item> implements List61B<Item>{...}
/* In my opinion, it looks like finding the same and integrating them. */
Interface Inheritance shows the subclasses are able to do, only claiming method head.//What
Implementation Inheritance tells subclasses how to behave,using “default” key word to define a method in interface.//How

Above is an Interface Inheritance. And actually “public void xxx” public is redundant.

Above is an Implementation Inheritance.



It will compile and add elk to the first of somelist! SLList(subclass) is a List61B(superclass).


Overload and Override:
Overload occurs when the method name is same, but the parameters are different
Override occurs when the method name and parameters are same, but one in superclass and one in subclass.
/* Use @Override tag on top of the method signature, so the compiler can check your error. Without the tag, still override. */



If we add these, and still declare: List61B<String> lst = new SSList<String> lst;
lst.print() will call the latter method(which is more suitable for SSList to print),.
/* There is “dynamic method selection” during overriding. 
* When Java runs a method that is overriden, it searches for the appropriate method signature in it's dynamic type and runs it.
* lst is of type List61B, a static type; And SSList dynamic type using new keyword, which can reassigned dynamically like new AList. 
* PLUS: The compiler determines whether or not something is valid based on the static type of the object.
* e.g.1 print() method only in SSList, if lst is of type “List61B”, lst.print() has a compiler error.
* e.g.2 SSList<String> list = lst has a complier error, because lst’s static type is List61B, but list SSList */

IMPORTANT: This does not work for overloaded methods!
e.g. methodTest(List61B<String> lst); methodTest(SSList<String> lst);
and still declare: List61B<String> lst = new SSList<String> lst;
methodTest(lst) will call the former one, because lst is of type List61B. 


** 1.31,2.1 finish Reading 4.2

Implements vs Extends:
Implements is for implementing an interface. Normally used when we know what to do
/* The process of “do” is in classes, we only declare what we can do in an interface and every method must be in arbitrary classes. */
Extends is for extending a class.Normally used when we know how to do
/* We can use almost every method, variable, nested classes.
* Constructors are not inherited, and private members cannot be directly accessed. */

When overriding, use super keyword to use superclass’s the same method
 /* It’s kind of like this.xxx, super.xxx */

Subclass builds constructor:
Subclass builds constructor must start with a call to one of its superclass's constructors.
/* Subclass “is a” superclass, so no superclass no subclass.
* Call super(xx); 
* If no call, Java default call the empty constructor of subclass. */


Every class extends Object (implicitly)
/* This link https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html shows Object class. */

Method calls have compiler-time types equal  their declared type


Use implements to build Higher Order Functions:
A higher order function is a function that treats other functions as data.



** 2.2 finish Reading 4.3

Comparable, an interface which is already defined by Java to compare types.

Make a generic type to avoid casting
/* When something need to compare, implementing the interface and write the method */


Comparator
The default compare order is called natural order.(In this case, is the dog’s size)

Using comparator we can compare the objects in different orders
/* Builds a subclass implementing the interface and write the method */




** 2.3 finish Reading 6.1

List in Java
import java.util.List;
import java.util.ArrayList;

List<Integer> L = new ArrayList<>();
L.add(5);
System.out.println(L);

Set in Java
import java.util.Set;
import java.util.HashSet;

Set<String> s = new HashSet<>();
s.add(“Tokyo”);
System.out.println(s.contains(“Tokyo”));// true


** 2.4 finish Reading 6.2

Throw exceptions
throw new ExceptionObject(parameter1, ...)


** 2.5 finish Reading 6.3 & Reading 6.4

Loop for set:
Ugly loop

Nice loop


To enable ugly loop:
	0.	javaset must have method iterator() returns Iterator<T>
	0.	Iterator<T> must have methods hasNext() returns boolean and next() returns T
/* Iterator is a built-in interface */ 



To enable nice loop:
	0.	You should tell Java that yours can be put in for loop - > to implement an interface called Iterable

	0.	So, you must have iterator method in your class which we build in ugly loop part.

When use “System.out.println(Object x)”, it calls x.toString()
/* The implementation of toString() in Object is: class name + @ + the memory location of the object */

/* If we just use “returnstring += items[i].toString()” to override the method, it’s slow!
* Because every string use “+” will create a totally new string. */

We use StringBuilder to override toString()


Equals()

 
** 2.6 finish lab 3

Perform random comparison test between two implementations of a class


** 2.7 finish Lab 4

Git Intro
Collaborate with others through git
Merge conflicts

Numbers between -128 ~ 127 using “==“ returns true， others return false
Integer a = 127; Integer b = 127;
a == b; // true
a = 128; b = 128;
a == b; // false
// Use .equals()
https://blog.csdn.net/chenliguan/article/details/53888018
/* differences between Integer and int
* auto boxing and unboxing
* pool */


** 2.8 finish part of Project 1

Packages
Import PackageName.ClassName.MethodName to use method in the package.
Typically, package names are the internet address of the entity writing the code, but backwards.
/* The JUnit library is hosted at junit.org, so the package is called org.junit.
* class called com.hrblock.TaxCalculator, which is different from com.turbotax.TaxCalculator. 
* package1 is com.hrblock, from hrblock.com. Though class name is same, package is different,so it can be valid */

Generate random numbers
	0.	import java.util.Random
	0.	use Math.random()


** 2.11 finish Project 1

comparable and comparator


** 2.12 finish Project 2 Preview

Git avoid redundancy with “Hashing”
Git uses the git -SHA1 hash(160 bits) to verify file integrity, and it adds security

the chance of having the same hash code is low low lower than using time(1/2^160)

Implements Serializable and use Utility class to write/read objects to/form files
/* A weird thing: there is no method in Serializable */


** 2.13 finish Reading 8.1 & Reading 8.2

Algorithm comparison:
	•	Consider only the Worst Case
	•	Restrict Attention to One Operation
	•	Eliminate Low Order Terms
	•	Eliminate Multiplicative Constants

Differences between Big Theta and Big O:


/* So, when given:
* 1. The worst case runtime is Θ(N^2)
* 2. The runtime is O(N^2)
* The first statement provides more information */


** 2.14 finish Reading 8.3 & Reading 8.4



Merge Sort:
Merge is O(N), and selection sort is O(N^2). Combine them to decrease the time, O(NlogN)
	0.	If the list is size 1, return. Otherwise:
	0.	Mergesort the left half
	0.	Mergesort the right half
	0.	Merge the results

Big Omega:
Big Omega can be thought of as the "greater than or equal".
If R(N) = O(f(N)) and R(N) = Ω(f(N)), then R(N) = Θ(f(N))



** 2.15 finish Reading 9.1 - Reading 9.5

Build Disjoint Set Interface:
/* To connect the sets that one has p, one has q */
void connect(int p, int q);
/* To check to see if they are connected */
boolean isConnect(int p, int q);

Quick Find

/* To check isConnected(x, y), we simply check if id[x] == id[y]. Note this is a constant time operation! */

Quick Union

And we need to define a helper method called finder(int x), which returns the index of the root of x
/* To connect, we simply set a[finder(q)] = finder(p); To check isConnected, finder(p) = finder(q)
* If the tree is long, call finder() is expensive with O(N)
* To improve it, we should decrease the height of the tree, where we introduce below */

Weighted Quick Union
New rule: whenever we call connect, we always link the root of the smaller(size smaller) tree to the larger(size bigger) tree
/* Following this will bring the trees a maximum height of logN and we can store the height in -(height of tree) instead of -1 in root
* We link the tree based on weight instead of height, because it’s easier and their runtime both are logN */


Weighted Quick Union with Path Compression
Connecting all the items along the way to the root will help make our tree shorter with each call to find
/* To decrease the time when we next call finder() */
The average runtime of connect and isConnected becomes almost constant, which is called the amortized runtime
More specifically, for M operations on N elements, WQU with Path Compression is in O(N + M (lg* N)). lg* is the iterated logarithm which is less than 5 for any real-world input.

Comparison




** 2.16 finish Reading 10.1 & Reading 10.2

Binary Search Tree Properties:
One key has either 0, 1, or 2 children
Every key in the left subtree is less than X’s key.
Every key in the right subtree is greater than X’s key.

Binary Search Tree Operations:
Search

Insert
/* We always insert at a leaf node */
 
Delete
/* No children: delete its parent pointer
* One child: reassign the parent's child pointer to the node's child
* Two children: choose the predecessor or successor to replace the key */


/* To delete “k”, we choose “g” or “m”
 * If choose “g”, first delete “g”, then replace “k” with “g” */


** 2.17 finish part of Lab 6

To run a Java file that is within a package, we must enter the parent directory and use the fully canonical name
/* e.g. Main.class is in lab6/capers
* If we just “cd capers” and “java Main”, it goes wrong
* We should just “cd lab6” and “java capers.Main” */

Use Java File class to create a file or directory


** 2.20 finish Lab 6

Current Working Directories(CWD)
The CWD of a Java program is the directory from where you execute that Java program. You can access the CWD from within a Java program by using System.getProperty("user.dir"). 

Absolute path and relative path



** 2.21 finish Reading 11.1 - Reading 11.5

B-Tree
	•	All leaves must be the same distance from the source
	•	A non-leaf node with k items must have exactly k + 1 children
/* These invariants guarantee that our trees will be bushy */

B-Tree deletion
take 2-3 Tree(which means a node has 2 keys, 3 children at most) as example
	0.	delete from a node with 2 or more children


	0.	delete from a leaf node with multiple keys, just delete it

	0.	delete from a leaf node with a single key, we can’t do this


Filling in empty nodes
	0.	the empty node’s adjacent sibling has multiple keys


	0.	siblings on the right all have one key, but parent has two


	0.	The parent and all siblings have only one item


Tree Rotation
Given any BST, it’s possible to move to a different configuration in 2n - 6 rotation
Rotation can shorten(or lengthen) a tree and preserve search tree properties

Left Leaning Red Black Binary Search Tree(LLRB)
Red means glue links that connect originally two keys on one node; Black means originally existed links
LLRB is the BST that 2-3 tree change into
	•	No node has two red links
	•	Every path from root to a leaf has same number of black links
If 2-3 tree’s height is H, then LLRB’s height is no more than 2H - 1


LLRB insertion
	0.	When inserting: Use a red link


	0.	If there is a right leaning “3-node”, we have a Left Leaning Violation
/* Rotate left the appropriate node to fix */


	0.	If there are two consecutive left links, we have an Incorrect 4 Node Violation
/* Rotate right the appropriate node to fix */


	0.	If there are any nodes with two red children, we have a Temporary 4 Node
/* Color flip(file the colors of all edges touching the node) the node to emulate the spilt operation */


	0.	NOTICE: It’s possible that a rotation/flip will cause an additional violation that needs fixing


** 2.22 finish Reading 12.1 - Reading 12.5

Hash Table
Great performance in practice
Don’t require items to be comparable
Implementations often relatively simple
In Java, implemented as java.util.HashMap and java.util.HashSet
/* All objects in java implement a .hashCode() method
* Use Math.floorMod(xx.hashcode(), M) to get index, if just use %, can result in negative index */

Hash table resizing
Suppose we have:
	•	The number of buckets M
	•	An increasing number of items N
Runtime is Θ(Q), Q is the max length of buckets, and N / M ≤ Q ≤ N. To decrease runtime, we choose to lower N / M
i.e An increasing number of buckets M
/* N / M is called the “load factor” representing how full the hash table is. When N / M is ≥ 1.5, then double M
* In this way, M = Θ(N), then O(N / M) = O(1) */



Consider the cost of the resize operation
	0.	Resizing takes Θ(N) time. Have to redistribute all items
	0.	Most add operations will be Θ(1). Some will be Θ(N) time(to resize)

NOTICE



** 2.23 finish Reading 13.1 - Reading 13.3 & Lab 7

Represent a tree in java:
	0.	Fixed-Width Nodes


	0.	Variable-Width Nodes


	0.	Sibling Tree


	0.	Store keys in an array, and parents in an array


	0.	Simply use an array

/* if a node is in key[x], its parent is in key[(x - 1) / 2]

	0.	More of “5”, change index 0 in array into empty, like sentinel



Build BSTMap
We need to overload original method which only has one parameter with two parameters(one is the node)
And we need to write a helper method called getMinChild(Node node)


Priority Queue
An ADT that optimizes for handling minimum or maximum elements
Only allow for size(), add(), getMin(), removeMin()
/* BST to build it is no so good, because BST doesn’t allow for same keys
* Instead we use heap */

Heap
take Binary min-heap as example
/* Binary tree that is complete(every node except bottom level has two children) and obeys min-heap property */
Min-heap property:
	•	Min-heap: Every node is less than or equal to both of its children
	•	Complete: Missing items only at the bottom level, all nodes are as left as possible

/* Heap’s getMin() is Θ(1), if BST keeps a pointer to smallest, its getMin is Θ(1) as well
* Array based heaps take less memory(roughly 1/3 the memory of the tree built in approach 1 above) */

Heap Insertion
	0.	Add x into the bottom temporarily keeping min-heap property
	0.	Compare x with its root, if x is smaller, exchange.

Heap Deletion
	0.	Swap the last item in the heap into the root
	0.	Then sink it down the hierarchy




** 2.24 finish Reading 17.1 - Reading 17.4

Tree Traversal
	0.	Level Order: Visit top-to-bottom, left-to-right
	0.	Depth First Traversal: Traverse “deep nodes” before “shallow ones”          NOTE:Traverse is not Visit
	0.	Preorder: Visit a node, then traverse its children
	0.	Inorder: Traverse left child, visit, then traverse right child
	0.	Postorder: Traverse left, traverse right, then visit
A Useful Visual Trick for humans to access the order

/* Draw around the tree, and go conterclockwise
* Preorder is go left
* Inorder is go under
* Postorder is go right */


Graph
	•	A set of vertices, a.k.a. nodes
	•	A set of zero or more edges, each of which connects two nodes
	•	Vertices with an edge between are adjacent
	•	Vertices or edges may have labels(or weights)
	•	A path is a sequence of vertices connected by edges
	•	A cycle is a path whose first and last vertices are the same // A graph with cycle is “cyclic”, else is “acyclic”
	•	Two vertices are connected if there is a path between them; The graph is connected if all vertices are connected

Simple Graph is a graph with:
	•	No edges that connect a vertex to itself, i.e. no “loops”
	•	No two edges that connect the same vertices, i.e. no “parallel edges”
We usually use simple graph


Depth First Search(DFS)


/* It can go as deep as we can, so called depth first
* It can go to every vertex in the graph, a.k.a. depth-first traversal
* DFS runtime is O(V + E), though E > V, we can’t say it O(E). Because even if E = 0, when comes to constructor mark, we still have V*/


** 2.25 finish Reading 18.1 & Reading 18.2

Breadth First Search(BFS)


/* O(V + E) as well. If use adjacency matrix, BFS and DFS become O(V ^ 2)*/

Graph Representation
	0.	Adjacency Matrix

	0.	Edge Sets: Collection of all edges

	0.	Adjacency lists




Undirected Graph Implementation



** 2.26 finish Lab 8

HashMap Inheritance Structure

/* Use ArrayList, HashSet, LinkedList, PriorityQueue, TreeSet to build its buckets */

Access Modifier

same class
same package
subclasses in other packages
other classes in other packages
public
✓
✓
✓
✓
protected
✓
✓
✓

（null）
✓
✓


private
✓











