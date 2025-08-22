package ru.streetfootball.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Set;

@Service
public class AvatarService {

    private static final long MAX_FILE_SIZE = 1024 * 1024;
    private static final Set<String> ALLOWED_IMAGE_TYPES = Set.of("image/jpeg", "image/png");
    private static final Set<String> ALLOWED_IMAGE_EXTENSIONS = Set.of("jpg", "jpeg", "png");

    @Value("${file.upload-dir:uploads/avatars}")
    private String uploadDir;

    /**
     * Получение аватара в виде Resource
     */
    public Resource getAvatar(Long telegramId) {
        try {
            var avatarPath = getAvatarPath(telegramId);
            if (avatarPath == null || !Files.exists(avatarPath)) {
                return null;
            }

            var resource = new UrlResource(avatarPath.toUri());
            if (resource.exists() && resource.isReadable()) {
                return resource;
            }

            return null;

        } catch (Exception e) {
            throw new RuntimeException("Failed to get avatar resource", e);
        }
    }

    public void uploadAvatar(Long telegramId, MultipartFile avatarFile) {
        validateImageFile(avatarFile);
        createUploadDirectory();
        var fileName = generateFileName(telegramId, avatarFile);
        saveFile(avatarFile, fileName);
    }

    /**
     * Валидация изображения
     */
    private void validateImageFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new RuntimeException("Avatar file is empty");
        }

        // Проверка размера файла
        if (file.getSize() > MAX_FILE_SIZE) {
            throw new RuntimeException("File size must is too large");
        }

        var contentType = file.getContentType();
        if (contentType == null || !ALLOWED_IMAGE_TYPES.contains(contentType)) {
            throw new RuntimeException("Invalid file type");
        }

        var originalFilename = file.getOriginalFilename();
        if (originalFilename != null) {
            var fileExtension = getFileExtension(originalFilename).toLowerCase();
            if (!ALLOWED_IMAGE_EXTENSIONS.contains(fileExtension)) {
                throw new RuntimeException("Invalid file extension");
            }
        }
    }

    /**
     * Создание директории для загрузки
     */
    private void createUploadDirectory() {
        try {
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to create upload directory", e);
        }
    }

    /**
     * Генерация имени файла
     */
    private String generateFileName(Long telegramId, MultipartFile file) {
        var fileExtension = getFileExtension(file.getOriginalFilename());
        return telegramId + "." + fileExtension;
    }

    /**
     * Получение расширения файла
     */
    private String getFileExtension(String filename) {
        if (filename == null || filename.lastIndexOf(".") == -1) {
            throw new RuntimeException("File must have an extension");
        }
        return filename.substring(filename.lastIndexOf(".") + 1);
    }

    /**
     * Сохранение файла на диск
     */
    private void saveFile(MultipartFile file, String fileName) {
        try {
            var filePath = Paths.get(uploadDir).resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            throw new RuntimeException("Failed to save file", e);
        }
    }

    /**
     * Получение пути к аватару
     */
    public Path getAvatarPath(Long telegramId) {
        for (String ext : ALLOWED_IMAGE_EXTENSIONS) {
            var filePath = Paths.get(uploadDir).resolve(telegramId + "." + ext);
            if (Files.exists(filePath)) {
                return filePath;
            }
        }
        return null;
    }
}