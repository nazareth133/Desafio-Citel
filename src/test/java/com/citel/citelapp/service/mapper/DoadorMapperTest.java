package com.citel.citelapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DoadorMapperTest {

    private DoadorMapper doadorMapper;

    @BeforeEach
    public void setUp() {
        doadorMapper = new DoadorMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(doadorMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(doadorMapper.fromId(null)).isNull();
    }
}
