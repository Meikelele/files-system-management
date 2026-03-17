package pl.mbak.filesmanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mbak.filesmanager.dto.GlobalStatisticsResponse;
import pl.mbak.filesmanager.dto.ManagedFileResponse;
import pl.mbak.filesmanager.model.ManagedFile;
import pl.mbak.filesmanager.repository.FolderRepository;
import pl.mbak.filesmanager.repository.ManagedFileRepository;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticService {

    private final FolderRepository folderRepository;
    private final ManagedFileRepository managedFileRepository;

    public GlobalStatisticsResponse getGlobalStatistic() {
        long totalFolders = folderRepository.count();
        long totalFiles = managedFileRepository.count();

        List<ManagedFile> files = managedFileRepository.findAll();

        long totalSizeInBytes = files
                .stream()
                .mapToLong(ManagedFile::getSizeInBytes)
                .sum();

        double averageFileSizeInBytes = totalSizeInBytes == 0
                ? 0.0
                : (double) totalSizeInBytes / totalFiles;

        String largestFilename = files
                // tworze strumien, czyli taka tablice z obiektami
                .stream()
                // wybieram obiekt z najdluzsza nazwa
                .max(
                        Comparator.comparing(file -> file.getFilename().length())
                )
                // mapuje obiekt na String z nazwa pliku
                .map(ManagedFile::getFilename)
                // jesli lista jest pusta i dostane Optional.empty() to zwracam null
                .orElse(null);

        Long largestFileSize = files
                // zamiana na strumien obiektow
                .stream()
                // mapuje na wartosc Optional<Long>
                .mapToLong(ManagedFile::getSizeInBytes)
                // wybeiram najwiekszy z plikow
                .max()
                // jak dostane Optional.empty() to zwracam null
                .orElse(0L);

        return new GlobalStatisticsResponse(
                totalFolders,
                totalFiles,
                totalSizeInBytes,
                averageFileSizeInBytes,
                largestFilename,
                largestFileSize
        );
    }





}
