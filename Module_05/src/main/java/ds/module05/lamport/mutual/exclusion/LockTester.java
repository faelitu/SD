package ds.module05.lamport.mutual.exclusion;

import ds.module02.socket.tcp.linker.Linker;
import ds.util.Util;

public class LockTester {
    public static void main(String[] args) throws Exception {
        Linker linker = null;
        try {
            String baseName = args[0];
            int processId = Integer.parseInt(args[1]);
            int numberOfProcesses = Integer.parseInt(args[2]);
            linker = new Linker(baseName, processId, numberOfProcesses);
            
            Lock lamportMutualExclusionProcess = new LamportMutualExclusionProcess(linker);
           
            // Creates one thread for each process but i. each thread changes state for Runnable, not Running
            for (int i = 0; i < numberOfProcesses; i++)
               if (i != processId) {
                  new ListenerThread(i, lamportMutualExclusionProcess).start();
               }
            
            while (true) {
                System.out.println(processId + " is not in Critical Section (CS)");
                Util.mySleep(2000); // wait 2 seconds
                lamportMutualExclusionProcess.requestCriticalSection();
                Util.mySleep(2000);
                System.out.println(processId + " is in Critical Section(CS) *****");
                lamportMutualExclusionProcess.releaseCriticalSection();
            }
        }
        
        catch (InterruptedException e) {
            if (linker != null) 
            	linker.close();
        }
        
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
