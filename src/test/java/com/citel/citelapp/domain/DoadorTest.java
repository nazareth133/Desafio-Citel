package com.citel.citelapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.citel.citelapp.web.rest.TestUtil;

public class DoadorTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Doador.class);
        Doador doador1 = new Doador();
        doador1.setId(1L);
        Doador doador2 = new Doador();
        doador2.setId(doador1.getId());
        assertThat(doador1).isEqualTo(doador2);
        doador2.setId(2L);
        assertThat(doador1).isNotEqualTo(doador2);
        doador1.setId(null);
        assertThat(doador1).isNotEqualTo(doador2);
    }
}
