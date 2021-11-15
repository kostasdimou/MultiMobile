import java.util.Scanner;
import java.lang.Math;

class MultiMobile {
  Scanner in = new Scanner(System.in);

  final static double fixed = 12.00; // euro
  final static int freeSeconds = 1000; // seconds
  final static double costPerSecond = 0.02; // euro/second
  final static double costPerSMS = 0.08; // euro/SMS
  final static double fee = 0.05; // 5%
  final static double vat = 24.00; // 24%

  int time = 0;
  int sms = 0;
  
  double mobileCost = 0.00;
  double feeCost = 0.00;
  double vatCost = 0.00;
  double totalCost = 0.00;
  
  private void output(String title) {
    System.out.println("-----------" + title + "-----------");
    System.out.println("Time:  " + time);
    System.out.println("SMS:   " + sms);
    System.out.println("Cost:  " + mobileCost);
    System.out.println("Fee:   " + feeCost);
    System.out.println("Vat:   " + vatCost);
    System.out.println("Total: " + totalCost);
    System.out.println();
  }
    
  private double cost(double fixed, int time) {
    if(time > freeSeconds)
      time -= freeSeconds;
    else
      time = 0;
    mobileCost = fixed + costPerSecond * time + costPerSMS * sms;
    feeCost = mobileCost * fee;
    vatCost = (mobileCost + feeCost) * vat;
    totalCost = mobileCost + feeCost + vatCost;
    output(fixed > 0 ? "Fixed" : "Free");
    return totalCost;
  }

  public double fixedCost() {
	return cost(fixed, time);
  }

  public double freeCost() {
	// We add the freeSeconds as they will be excluded in the called method.
    return cost(0, time + freeSeconds);
  }

  private int askFor(String prompt) {
    System.out.print(prompt + ": ");
    int number = in.nextInt();
    return number;
  }
  
  public void input() {
      time = askFor("time");
      sms = askFor("SMS");
  }
  
  public int maxGain(double[] gain, int excludeIndex) {
    int max = -1;
    
    for(int i = 0; i < 5; i++) {
      if(i == excludeIndex)
        continue;
      if(max == -1)
        max = i;
      else
        if(gain[max] < gain[i])
          max = i;
    }
    return max;
  }
  
  public static void main(String[] arguments) {
    double[] gain = new double[] {0,0,0,0,0};
	MultiMobile mobile = new MultiMobile();
    
    for(int i = 0; i < 5; i++) {
      mobile.input();
      gain[i] = Math.abs(mobile.fixedCost() - mobile.freeCost());
    }
    
    int max1 = mobile.maxGain(gain, -1);
    System.out.println("1st maximum difference " + gain[max1] + " at position " + max1);
    int max2 = mobile.maxGain(gain, max1);
    System.out.println("2nd maximum difference " + gain[max2] + " at position " + max2); 
  }
}
