package com.cybertek.implementation;

import com.cybertek.dto.ProjectDTO;
import com.cybertek.entity.Project;
import com.cybertek.mapper.ProjectMapper;
import com.cybertek.repository.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectServiceImplTest {


    @Mock
    ProjectRepository projectRepository;

    @Mock
    ProjectMapper projectMapper;

    @InjectMocks
    ProjectServiceImpl projectService;

    @Test
    void getProjectCode() {

        Project project = new Project();
        ProjectDTO dto = new ProjectDTO();

        when(projectRepository.findByProjectCode("PR01")).thenReturn(project);
        when(projectMapper.convertToDto(project)).thenReturn(dto);

        ProjectDTO projectDTO = projectService.getProjectCode("PR01");

        verify(projectRepository).findByProjectCode("PR01");
        verify(projectMapper).convertToDto(Mockito.any(Project.class));

        assertNotNull(projectDTO);
    }

    @Test
    void getProjectCodeBDD(){
        Project project = new Project();
        ProjectDTO dto = new ProjectDTO();

        //given
        given(projectRepository.findByProjectCode("PR01")).willReturn(project);
        given(projectMapper.convertToDto(project)).willReturn(dto);

        // when
        ProjectDTO projectDTO = projectService.getProjectCode("PR01");

        // then
        then(projectRepository).should().findByProjectCode("PR01");
        then(projectMapper).should().convertToDto(Mockito.any(Project.class));

        assertNotNull(projectDTO);

    }

    @Test
    void getByProjectCode_exception_test(){
        when(projectRepository.findByProjectCode("")).thenThrow(new RuntimeException("Project Not Found!"));

        Throwable exception = assertThrows(RuntimeException.class, ()-> projectService.getProjectCode(""));

        verify(projectRepository).findByProjectCode(Mockito.anyString());

        assertEquals(exception.getMessage(), "Project Not Found!");

    }

    @Test
    void saveProject(){

        Project project = new Project();
        ProjectDTO projectDTO = new ProjectDTO();

        when(projectMapper.convertToEntity(projectDTO)).thenReturn(project);
        when(projectRepository.save(project)).thenReturn(project);
        projectService.save(projectDTO);

        verify(projectRepository).save(project);
    }


}