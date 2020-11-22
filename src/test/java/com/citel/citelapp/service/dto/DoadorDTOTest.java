package com.citel.citelapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.citel.citelapp.web.rest.TestUtil;

public class DoadorDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DoadorDTO.class);
        DoadorDTO doadorDTO1 = new DoadorDTO();
        doadorDTO1.setId(1L);
        DoadorDTO doadorDTO2 = new DoadorDTO();
        assertThat(doadorDTO1).isNotEqualTo(doadorDTO2);
        doadorDTO2.setId(doadorDTO1.getId());
        assertThat(doadorDTO1).isEqualTo(doadorDTO2);
        doadorDTO2.setId(2L);
        assertThat(doadorDTO1).isNotEqualTo(doadorDTO2);
        doadorDTO1.setId(null);
        assertThat(doadorDTO1).isNotEqualTo(doadorDTO2);
    }
}
