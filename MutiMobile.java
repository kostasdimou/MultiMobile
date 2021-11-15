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
  
  private void output() {
    System.out.print("Time:  " + time);
    System.out.print("SMS:   " + sms);
    System.out.print("Cost:  " + mobileCost);
    System.out.print("Fee:   " + feeCost);
    System.out.print("Vat:   " + vatCost);
    System.out.print("Total: " + totalCost);
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
    output();
    return totalCost;
  }

  private double freeCost() {
    return cost(0, time + freeSeconds);
  }

  private int input(String prompt) {
    System.out.print(prompt + ": ");
    int number = in.nextInt();
    return number;
  }
  
  private int maxGain(double[] gain, int excludeIndex) {
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
    
    for(int i = 0; i < 5; i++) {
      time = input("time");
      sms = input("SMS");
      gain[i] = Math.abs(cost() - freeCost());
    }
    
    int max1 = maxGain(gain, -1);
    System.out.print("1st maximum difference " + gain[max1] + " at position " + max1);
    int max2 = maxGain(gain, max1);
    System.out.print("2nd maximum difference " + gain[max2] + " at position " + max2); 
  }
}
