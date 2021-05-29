//code examples followed by Algorithms canvas online textbook

public class Btree {
	public static void main(String[] args) {
		Btree Btree = new Btree();
	}
	
	Node balanceRight(Node lefty) {
		Node righty = lefty.left;
		righty.right = lefty;
		Node prev = righty.right;
		lefty.left = prev;
		lefty.level = largest(level(lefty.left), level(lefty.right)) + 1;
		righty.level = largest(level(righty.left), level(righty.right)) + 1;
		
		return righty;
	}

	Node balanecLeft(Node righty) {
		Node lefty = righty.right;
		lefty.left = righty;
		Node prev = lefty.left;
		righty.right = prev;
		righty.level = largest(level(righty.left), level(righty.right)) + 1;
		lefty.level = largest(level(lefty.left), level(lefty.right)) + 1;
		
		return lefty;
	}

	Node insert(Node createNew, int entry) {
		// this is the entry part
		if (createNew == null) {
			return new Node(entry);
		}
		if (entry < createNew.entry) {
			createNew.left = insert(createNew.left, entry);
		} else if (entry > createNew.entry) {
			createNew.right = insert(createNew.right, entry);
		} else {
			return createNew;
		}
		createNew.level = 1 + largest(level(createNew.left), level(createNew.right));

		// this is the reblancing part
		int balance = isBalanced(createNew);
		if (balance < -1 && entry > createNew.right.entry) {
			return balanecLeft(createNew);
		}
		if (balance > 1 && entry < createNew.left.entry) {
			return balanceRight(createNew);
		}
		if (balance < -1 && entry < createNew.right.entry) {
			createNew.right = balanecLeft(createNew.right);
			return balanecLeft(createNew);
		}
		if (balance > 1 && entry > createNew.left.entry) {
			createNew.left = balanecLeft(createNew.left);
			return balanceRight(createNew);
		}

		return createNew;
	}

	int isBalanced(Node balance) {
		if (balance == null) {
			return 0;
		} else {
			return level(balance.left) - level(balance.right);
		}
	}

	int largest(int first, int second) {
		if (first >= 2) {
			return first;
		} else
			return second;
	}

	int level(Node curr) {
		if (curr == null)
			return 0;
		else
			return curr.level;
	}

	Node enterTheTree;
}