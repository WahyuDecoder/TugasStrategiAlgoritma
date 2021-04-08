package tugas.strategiAlgoritma.towerofhanoi;

import java.util.Stack;

public class Main {

    public static void main(String args[]) {
        new MainFrame();
//        algoritmaHanoi(6, 1, 2, 3);
//        System.out.println("");
//        algoritmaHanoi(6);
    }

    public static void algoritmaHanoi(int n, int asal, int sementara, int tujuan) {
        if (n == 0) {
            return;
        }
        algoritmaHanoi(n - 1, asal, tujuan, sementara);
        System.out.println("Pindah " + n + " dari menara " + asal + " untuk menara " + tujuan + ".");
        algoritmaHanoi(n - 1, sementara, asal, tujuan);
    }

    public static void algoritmaHanoi(int n) {
        Stack<Integer>[] menaras = new Stack[4];
        menaras[1] = new Stack<Integer>();
        menaras[2] = new Stack<Integer>();
        menaras[3] = new Stack<Integer>();
        for (int i = n; i >= 1; i--) {
            menaras[1].push(i);
        }
        int jejak = 0;
        if (n % 2 == 1) {
            jejak = menaras[1].pop();
            menaras[3].push(jejak);
            System.out.println("Pindah " + jejak + " dari menara " + 1 + " untuk menara " + 3 + ".");
        } else {
            jejak = menaras[1].pop();
            menaras[2].push(jejak);
            System.out.println("Pindah " + jejak + " dari menara " + 1 + " untuk menara " + 2 + ".");
        }

        for (int i = 2; i < (int) Math.pow(2, n); i++) {
            if (i % 2 == 1) {
                
                int men = 99;
                int menara = 0;
                if (!menaras[1].isEmpty() && menaras[1].peek() < men) {
                    men = menaras[1].peek();
                    menara = 1;
                }
                if (!menaras[2].isEmpty() && menaras[2].peek() < men) {
                    men = menaras[2].peek();
                    menara = 2;
                }
                if (!menaras[3].isEmpty() && menaras[3].peek() < men) {
                    men = menaras[3].peek();
                    menara = 3;
                }
                jejak = menaras[menara].pop();
                if (n % 2 == 1) {
                    if (menara == 1) {
                        menaras[3].push(jejak);
                        System.out.println("Pindah " + jejak + " dari menara " + menara + " untuk menara " + 3 + ".");
                    } else {
                        menaras[menara - 1].push(jejak);
                        System.out.println("Pindah " + jejak + " dari menara " + menara + " untuk menara " + (menara - 1) + ".");
                    }
                } else {
                    if (menara == 3) {
                        menaras[1].push(jejak);
                        System.out.println("Pindah " + jejak + " dari menara " + menara + " untuk menara " + 1 + ".");
                    } else {
                        menaras[menara + 1].push(jejak);
                        System.out.println("Pindah " + jejak + " dari menara " + menara + " untuk menara " + (menara + 1) + ".");
                    }
                }
            } else {
                boolean sw = true;
                if (!menaras[1].isEmpty() && menaras[1].peek() != jejak && sw) {
                    if (menaras[2].isEmpty()) {
                        jejak = menaras[1].pop();
                        menaras[2].push(jejak);
                        System.out.println("Pindah " + jejak + " dari menara " + 1 + " untuk menara " + 2 + ".");
                        sw = false;
                    } else {
                        if (sw && menaras[1].peek() < menaras[2].peek()) {
                            jejak = menaras[1].pop();
                            menaras[2].push(jejak);
                            System.out.println("Pindah " + jejak + " dari menara " + 1 + " untuk menara " + 2 + ".");
                            sw = false;
                        }
                    }
                    if (menaras[3].isEmpty()) {
                        jejak = menaras[1].pop();
                        menaras[3].push(jejak);
                        System.out.println("Pindah " + jejak + " dari menara " + 1 + " untuk menara " + 3 + ".");
                        sw = false;
                    } else {
                        if (sw && menaras[1].peek() < menaras[3].peek()) {
                            jejak = menaras[1].pop();
                            menaras[3].push(jejak);
                            System.out.println("Pindah " + jejak + " dari menara " + 1 + " untuk menara " + 3 + ".");
                            sw = false;
                        }
                    }
                }
                if (!menaras[2].isEmpty() && menaras[2].peek() != jejak && sw) {
                    if (menaras[1].isEmpty()) {
                        jejak = menaras[2].pop();
                        menaras[1].push(jejak);
                        System.out.println("Pindah " + jejak + " dari menara " + 2 + " untuk menara " + 1 + ".7897");
                        sw = false;
                    } else {
                        if (sw && menaras[2].peek() < menaras[1].peek()) {
                            jejak = menaras[2].pop();
                            menaras[1].push(jejak);
                            System.out.println("Pindah " + jejak + " dari menara " + 2 + " untuk menara " + 1 + ".");
                            sw = false;
                        }
                    }
                    if (menaras[3].isEmpty()) {
                        jejak = menaras[2].pop();
                        menaras[3].push(jejak);
                        System.out.println("Pindah " + jejak + " dari menara " + 2 + " untuk menara " + 3 + ".");
                        sw = false;
                    } else {
                        if (sw && menaras[2].peek() < menaras[3].peek()) {
                            jejak = menaras[2].pop();
                            menaras[3].push(jejak);
                            System.out.println("Pindah " + jejak + " dari menara " + 2 + " untuk menara " + 3 + ".");
                            sw = false;
                        }
                    }
                }
                if (!menaras[1].isEmpty() && menaras[1].peek() != jejak && sw) {
                    if (menaras[1].isEmpty()) {
                        jejak = menaras[3].pop();
                        menaras[1].push(jejak);
                        System.out.println("Pindah " + jejak + " dari menara " + 3 + " untuk menara " + 1 + ".");
                        sw = false;
                    } else {
                        if (sw && menaras[3].peek() < menaras[1].peek()) {
                            jejak = menaras[3].pop();
                            menaras[1].push(jejak);
                            System.out.println("Pindah " + jejak + " dari menara " + 3 + " untuk menara " + 1 + ".");
                            sw = false;
                        }
                    }
                    if (menaras[2].isEmpty()) {
                        jejak = menaras[3].pop();
                        menaras[2].push(jejak);
                        System.out.println("Pindah " + jejak + " dari menara " + 3 + " untuk menara " + 2 + ".");
                        sw = false;
                    } else {
                        if (sw && menaras[3].peek() < menaras[2].peek()) {
                            jejak = menaras[3].pop();
                            menaras[2].push(jejak);
                            System.out.println("Pindah " + jejak + " dari menara " + 3 + " untuk menara " + 2 + ".");
                            sw = false;
                        }
                    }
                }
            }
        }
    }
}
