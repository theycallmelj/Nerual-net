public class Rnd2048 {
	/*
	 * Generate a new value that is set to either 2 or 4, to add
	 * into the board.
	 */
	public static int randValue() {
		/*
		 * We always return two so we can easily test the
		 * program.  If you want random: 
		 *
		 * return (int)((Math.random() * 2) + 1) * 2;
		 */
		return 2;
	}

	/*
	 * Generate a random number with a specific ceiling (i.e. the
	 * returned int should be between 0 and ceil, not including
	 * ceil).
	 *
	 * We provide a version here that is _not_ random.  This will
	 * enable our assertions and easier testing (it is hard to
	 * test random results!!!).
	 *
	 * Use:
	 * return (int)(Math.random() * ceil);
	 * for non-deterministic random numbers.
	 */
	private static int x = 7, y = 3, z = 127, w = 2345;
	public static int randNum(int ceil) {
		// Attribution:  this non-crypto quality PSG taken from
		// wikipedia.org/wiki/Xorshift
    if (ceil == 0)
    {
      return 0;
    }
		int t = x ^ (x << 11);
		x = y;
		y = z;
		z = w;
		w = w^(w>>19)^t^(t>>8);
		
		return w % ceil;

		//return (int)(Math.random() * ceil);
	}
}
