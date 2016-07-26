package hw1;/**
 * 
 * @author Ming
 *
 */

public class ParkingMeter {
	/**
	* Value of dime coin in cents.
	*/
	public static final int DIME_VALUE = 10;
	/**
	* Value of nickel coin in cents.
	*/
	public static final int NICKEL_VALUE = 5;
	/**
	* Value of quarter coin in cents.
	*/
	public static final int QUARTER_VALUE = 25;
	
	private int givenMinutesForNickel;
	private int givenMinutesForDime;
	private int givenMinutesForQuarter;
	private int maxTime;
	private int maxCoins;
	private int totalCoins;
	private int numberNickel;
	private int numberDime;
	private int numberQuarter;
	private int MaxTime;
	private int MaxCoins;
	private int minutesleft;

	
	
		public ParkingMeter(int givenMinutesForNickel,
			int givenMinutesForDime,
			int givenMinutesForQuarter,
			int givenMaxTime,
			int givenMaxCoins)
		{
		this.givenMinutesForNickel = givenMinutesForNickel;
		this.givenMinutesForDime = givenMinutesForDime;
		this.givenMinutesForQuarter = givenMinutesForQuarter;
		this.MaxTime = givenMaxTime;
		this.MaxCoins = givenMaxCoins;
		this.minutesleft = givenMinutesForDime*numberDime+givenMinutesForNickel*numberNickel+givenMinutesForQuarter*numberQuarter;
		}
		/**
		 * empties all coins from this meter, without modifying the remaining time.
		 */
		public void emptyCoins()
		{
		totalCoins = 0;
		numberNickel = 0;
		numberDime = 0;
		numberQuarter = 0;
		}
		/**
		 * Returns the total amount of money, in cents, contained in this meter.
		 * @return int
		 */
		public int getCents()
		{
		return numberNickel*5+numberDime*10+numberQuarter*25;
		}
		/**
		 * Return a String representing the total amount of money in this meter as a dollar amount
		showing exactly two decimal places.
		 * @return
		 */
		public String getDollarString()
		{
			String dollarString = String.format("%01.2f", Double.parseDouble(getCents()+"")/100);
			return dollarString;
		}
		/**
		 * Returns a String representing the time remaining on this meter in the form "hh:mm".
		 * @return
		 */
		public String getHourMinuteString()
		{
			
			String HourMinutes = String.format("%02d:%02d",getMinutesRemaining()/60, getMinutesRemaining()%60);
			return HourMinutes;
		}
		/**
		 * Returns the amount of time, in minutes, remaining on this meter
		 * @return
		 */
		public int getMinutesRemaining()
		{
			return minutesleft;
		}
		/**
		 * Returns the number of coins currently in this meter
		 * @return
		 */
		public int getTotalCoins()
		{
			
			return numberNickel+numberDime+numberQuarter;
		}
		/**
		 * Inserts a given number of dimes into this meter, increasing the remaining time by an
		appropriate amount
		 * @param howMany
		 */
		public void insertDimes(int howMany)
		{
		/**	numberDime = numberDime + howMany;
			int nowMinutes = getMinutesRemaining() + howMany*givenMinutesForDime;
			if(nowMinutes>MaxTime){
				nowMinutes = MaxTime;
		 	}
		*/
			if(howMany <= 0) return;
			if((howMany+getTotalCoins())>MaxCoins){
				totalCoins=MaxCoins;
				minutesleft = getMinutesRemaining()+(MaxCoins-getTotalCoins())*givenMinutesForDime;
				numberDime = numberDime + (MaxCoins-getTotalCoins());
			}
			else{
				numberDime=numberDime + howMany;
				minutesleft = getMinutesRemaining()+howMany*givenMinutesForDime;
				
			}
			if(minutesleft>MaxTime){
				minutesleft = MaxTime;
			}
		}
		/**
		 * Inserts a given number of dimes into this meter, increasing the remaining time by an
		appropriate amount
		 * @param howMany
		 */
		public void insertNickels(int howMany)
		{
		/**	numberNickel = numberNickel + howMany;
			int nowMinutes = getMinutesRemaining() + howMany*givenMinutesForDime;
			if(nowMinutes>MaxTime){
				nowMinutes = MaxTime;
			}
		*/
			if(howMany <= 0) return;
			if((howMany+getTotalCoins())>MaxCoins){
				totalCoins=MaxCoins;
				minutesleft = getMinutesRemaining()+(MaxCoins-getTotalCoins())*givenMinutesForNickel;
				numberNickel = numberNickel + (MaxCoins-getTotalCoins());
			}
			else{
				numberNickel=numberNickel + howMany;
				minutesleft = getMinutesRemaining()+howMany*givenMinutesForNickel;

			}
			if(minutesleft>MaxTime){
				minutesleft = MaxTime;
			}
		}
		/**
		 * Inserts a given number of dimes into this meter, increasing the remaining time by an
		appropriate amount
		 * @param howMany
		 */
		public void insertQuarters(int howMany)
		{
		/**numberQuarter = numberQuarter + howMany;
		int nowMinutes = getMinutesRemaining() + howMany*givenMinutesForDime;
		if(nowMinutes>MaxTime){
			nowMinutes = MaxTime;
			}
		*/
			if(howMany <= 0) return;
			if((howMany+getTotalCoins())>MaxCoins){
				totalCoins=MaxCoins;
				minutesleft = getMinutesRemaining()+(MaxCoins-getTotalCoins())*givenMinutesForQuarter;
				numberQuarter = numberQuarter + (MaxCoins-getTotalCoins());
			}
			else{
				numberQuarter=numberQuarter + howMany;
				minutesleft = getMinutesRemaining()+howMany*givenMinutesForQuarter;

			}
			if(minutesleft>MaxTime){
				minutesleft = MaxTime;
			}
		}
		/**
		 * Returns true if there is no time remaining, false otherwise.
		 * @return
		 */
		public boolean isExpired()
		{
			if(getMinutesRemaining()==0){
				return true;
			}
				return false;
		}

		/**
		 * Simulates the passage of time for the given number of minutes. Does nothing if the given
		value is negative.
		 * @param minutes
		 */
		public void simulateTime(int minutes)
		{
			/*try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			minutesleft = minutes - 1;*/
			if(minutes <= 0) return;
			minutesleft = minutesleft - minutes;
			if(minutesleft < 0) minutesleft = 0;
		}
		/**
		 * Returns a String representation of this object in exactly the following format:
		 */
		public String toString()
		{
			return "hw1.ParkingMeter [Time" +getMinutesRemaining()+"/"+MaxTime+", Coins "+getTotalCoins()+"/"+MaxCoins+", Value "+getCents()+" ]";
		}
}
