package com.github.sacredrelict.data.repository;

import com.github.sacredrelict.data.ApplicationTest;
import com.github.sacredrelict.data.buider.RoleBuilder;
import com.github.sacredrelict.data.entity.Role;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Test RoleRepository.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {
        ApplicationTest.class})
@Transactional
public class RoleRepositoryTest {

    @Autowired
    @InjectMocks
    private RoleRepository roleRepository;

    private RoleBuilder roleBuilder;
    private Role role;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);

        roleBuilder = new RoleBuilder();
        role = roleBuilder.createRole();
    }

    @Test
    @Rollback
    public void saveTest_createRole_noException() {
        Role testRole = roleRepository.save(role);
        assertNotNull(testRole);
        assertNotNull(testRole.getId());
    }

    @Test
    @Rollback
    public void deleteTest_deleteRole_noException() {
        Role testRole = roleRepository.save(role);
        assertNotNull(testRole);
        assertNotNull(testRole.getId());

        roleRepository.delete(testRole);
        assertNull(roleRepository.findById(testRole.getId()));
    }

}
