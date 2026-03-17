package pl.mbak.filesmanager.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.mbak.filesmanager.dto.GlobalStatisticsResponse;
import pl.mbak.filesmanager.model.ManagedFile;
import pl.mbak.filesmanager.repository.FolderRepository;
import pl.mbak.filesmanager.repository.ManagedFileRepository;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StatisticServiceTest {

    @Mock
    private FolderRepository folderRepository;

    @Mock
    private ManagedFileRepository managedFileRepository;

    @InjectMocks
    private StatisticService statisticService;


    @Test
    void shouldReturnCorrectGlobalStatisticResponseWhenFilesExist() {

        // Arrange
        ManagedFile file1 = new ManagedFile();
        file1.setSizeInBytes(100L);
        file1.setFilename("invoice.pdf");

        ManagedFile file2 = new ManagedFile();
        file2.setSizeInBytes(300L);
        file2.setFilename("document.txt");

        when(folderRepository.count()).thenReturn(5L);
        when(managedFileRepository.count()).thenReturn(2L);
        when(managedFileRepository.findAll()).thenReturn(List.of(file1, file2));

        // Act
        GlobalStatisticsResponse result = statisticService.getGlobalStatistic();

        // Assertion
        assertEquals(5L, result.getTotalFolders());
        assertEquals(2L, result.getTotalFiles());
        assertEquals(400L, result.getTotalSizeInBytes());
        assertEquals((double) 400L / 2L, result.getAvgFileSize());
        assertEquals(file2.getFilename(), result.getLargestFileName());
        assertEquals(file2.getSizeInBytes(), result.getLargestFileSize());
    }

    @Test
    void shouldReturnZeroAverageWhenNoFilesExist() {
        // Arrange
        when(folderRepository.count()).thenReturn(3L);
        when(managedFileRepository.count()).thenReturn(0L);
        when(managedFileRepository.findAll()).thenReturn(List.of());

        // Act
        GlobalStatisticsResponse result = statisticService.getGlobalStatistic();

        // Assertion
        assertEquals(3L, result.getTotalFolders());
        assertEquals(0L, result.getTotalFiles());
        assertEquals(0L, result.getTotalSizeInBytes());
        assertEquals(0.0, result.getAvgFileSize());
    }
}
