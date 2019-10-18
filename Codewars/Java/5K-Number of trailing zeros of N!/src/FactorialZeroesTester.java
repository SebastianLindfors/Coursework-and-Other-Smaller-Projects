import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class FactorialZeroesTester {

    @Test
    public void testZeros() throws Exception {
        assertThat(FactorialZeroes.zeros(0), is(0));
        assertThat(FactorialZeroes.zeros(6), is(1));
        assertThat(FactorialZeroes.zeros(14), is(2));
        assertThat(FactorialZeroes.zeros(10), is(2));
        assertThat(FactorialZeroes.zeros(13), is(2));
        assertThat(FactorialZeroes.zeros(16), is(3));
    }


}
