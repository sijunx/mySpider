package call_back;

import java.util.concurrent.Callable;

class ThreadDemo implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int sum = 0;

        for (int i = 0; i <= 100000; i++) {
            sum += i;
        }

        return sum;
    }

}
