package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * Creates and defines behaviors and methods for a linked binary tree
 * 
 * @author Gianna Mastrandrea
 *
 * @param <E> generic object
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

	private Node<E> root;
	private int size;

	/**
	 * Creates new linked binary tree with null root and size zero
	 */
	public LinkedBinaryTree() {
		root = null;
		size = 0;
	}

	/**
	 * Validates given positional node
	 * 
	 * @param p position
	 * @return node at given position if valid
	 */
	protected Node<E> validate(Position<E> p) {
		if (!(p instanceof Node)) {
			throw new IllegalArgumentException("Position is not a valid linked binary node");
		}
		return (Node<E>) p;
	}

	/**
	 * Creates and defines behaviors and methods of a linked binary tree node
	 * 
	 * @author Gianna Mastrandrea
	 *
	 * @param <E> generic object
	 */
	public static class Node<E> extends AbstractNode<E> {
		private Node<E> parent;
		private Node<E> left;
		private Node<E> right;

		/**
		 * Creates new node with given element
		 * 
		 * @param element element
		 */
		public Node(E element) {
			this(element, null);
		}

		/**
		 * Creates new node with given element and parent
		 * 
		 * @param element element
		 * @param parent  parent of node
		 */
		public Node(E element, Node<E> parent) {
			super(element);
			setParent(parent);
		}

		/**
		 * Gets left child of node
		 * 
		 * @return left child of node
		 */
		public Node<E> getLeft() {
			return left;
		}

		/**
		 * Gets right child of node
		 * 
		 * @return right
		 */
		public Node<E> getRight() {
			return right;
		}

		/**
		 * Sets left child of node
		 * 
		 * @param left left child node
		 */
		public void setLeft(Node<E> left) {
			this.left = left;
		}

		/**
		 * Sets right child of node
		 * 
		 * @param right right child node
		 */
		public void setRight(Node<E> right) {
			this.right = right;
		}

		/**
		 * Gets parent of node
		 * 
		 * @return parent of node
		 */
		public Node<E> getParent() {
			return parent;
		}

		/**
		 * Sets parent of node
		 * 
		 * @param parent parent
		 */
		public void setParent(Node<E> parent) {
			this.parent = parent;
		}
	}

	@Override
	public Position<E> left(Position<E> p) {
		Node<E> node = validate(p);
		return node.getLeft();
	}

	@Override
	public Position<E> right(Position<E> p) {
		Node<E> node = validate(p);
		return node.getRight();
	}

	@Override
	public Position<E> addLeft(Position<E> p, E value) {
		Node<E> node = validate(p);
		if (left(node) != null) {
			throw new IllegalArgumentException("Node already has a left child.");
		}
		((Node<E>) p).setLeft(createNode(value, node, null, null));
		size++;
		return left(p);
	}

	@Override
	public Position<E> addRight(Position<E> p, E value) {
		Node<E> node = validate(p);
		if (right(node) != null) {
			throw new IllegalArgumentException("Node already has a right child.");
		}
		((Node<E>) p).setRight(createNode(value, node, null, null));
		size++;
		return right(p);
	}

	@Override
	public Position<E> root() {
		return root;
	}

	@Override
	public Position<E> parent(Position<E> p) {
		Node<E> node = validate(p);
		return node.getParent();
	}

	@Override
	public Position<E> addRoot(E value) {
		if (root() != null) {
			throw new IllegalArgumentException("The tree already has a root.");
		}
		this.root = createNode(value, null, null, null);
		size++;
		return root;
	}

	@Override
	public E remove(Position<E> p) {
		if (numChildren(p) == 2) {
			throw new IllegalArgumentException("The node has two children");
		}

		Node<E> node = validate(p);
		Node<E> parent = null;
		if (parent(p) != null) {
			parent = validate(parent(p));
		}

		if (node == null)
			return null;

		// if root
		if (node == root()) {
			E element = node.getElement();
			// replace with right child
			if (left(p) == null && right(p) != null)
				setRoot(right(p));

			// replace with left child
			else if (left(p) != null && right(p) == null)
				setRoot(left(p));

			size--;
			return element;
		}

		// replace with right child
		if (left(p) == null && right(p) != null) {
			node.getRight().setParent(parent);
			if (parent != null) {
				if (parent.getRight() == node) {
					parent.setRight(node.getRight());
				} else {
					parent.setLeft(node.getRight());
				}
			}

			// replace with left child
		} else if (left(p) != null && right(p) == null) {
			node.getLeft().setParent(parent);
			if (parent != null) {
				if (parent.getRight() == node) {
					parent.setRight(node.getLeft());
				} else {
					parent.setLeft(node.getLeft());
				}
			}

			// no children, just delete
		} else {
			if (parent == null) {
				E element = node.getElement();
				size--;
				setRoot(null);
				return element;
			}

			if (parent.getLeft().getElement() == node.getElement())
				parent.setLeft(null);
			else
				parent.setRight(null);
		}

		size--;
		return node.getElement();

	}

	@Override
	public int size() {
		return size;
	}

	/**
	 * Creates new node with given element, left child, right child, and parent
	 * 
	 * @param e      element
	 * @param parent parent
	 * @param left   left child
	 * @param right  right child
	 * @return node created
	 */
	protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
		Node<E> newNode = new Node<E>(e);
		newNode.setParent(parent);
		newNode.setLeft(left);
		newNode.setRight(right);
		return newNode;
	}

	// setRoot is needed for a later lab...
	// ...but THIS DESIGN IS BAD! If a client arbitrarily changes
	// the root by using the method, the size may no longer be correct/valid.
	// Instead, the precondition for this method is that
	// it should *ONLY* be used when rotating nodes in
	// balanced binary search trees. We could instead change
	// our rotation code to not need this setRoot method, but that
	// makes the rotation code messier. For the purpose of this lab,
	// we will sacrifice a stronger design for cleaner/less code.
	/**
	 * Sets root of tree
	 * 
	 * @param p positional node to set as root
	 * @return new root positional node
	 */
	protected Position<E> setRoot(Position<E> p) {
		root = validate(p);
		return root;
	}
}
