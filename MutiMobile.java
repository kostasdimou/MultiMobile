import java.util.Scanner;

class MultiMobile {
  Scanner in = new Scanner(System.in);

  const static float fixed = 12.00; // euro
  const static int freeSeconds = 1000; // seconds
  const static float costPerSecond = 0.02; // euro/second
  const static float costPerSMS = 0.08; // euro/SMS
  const static float fee = 0.05; // 5%
  const static float vat = 24.00; // 24%

  int time = 0;
  int sms = 0;
  
  float mobileCost = 0.00;
  float feeCost = 0.00;
  float vatCost = 0.00;
  float totalCost = 0.00;
  
  private void output(void) {
    System.out.print("Time:  " + time);
    System.out.print("SMS:   " + sms);
    System.out.print("Cost:  " + mobileCost);
    System.out.print("Fee:   " + feeCost);
    System.out.print("Vat:   " + vatCost);
    System.out.print("Total: " + totalCost);
  }
    
  private float cost(float fixed, int time) {
    if(time > freeSeconds)
      time -= freeSeconds;
    else
      time = 0;
    mobileCost = fixed + durationCost * time + smsCost * sms;
    feeCost = mobileCost * fee;
    vatCost = (mobileCost + feeCost) * vat;
    totalCost = mobileCost + feeCost + vatCost;
    return totalCost;
  }

  private float freeCost(void) {
    return cost(0, time + freeSeconds);
  }

  private int input(String prompt) {
    System.out.print(prompt + ": ");
    int number = in.nextInt();
    return number;
  }
  
  public static void main(String[] arguments) {
    float[] gain = new float[5] {0,0,0,0,0};
    for(int i = 0; i < 5; i++) {
      time = input("Χρόνος ομιλίας σε δευτερόλεπτα");
      sms = input("Πλήθος των SMS");
      gain[i] = cost() - freeCost();
    }
  }
}