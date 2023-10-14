package com.example.jsh_project.domain.Dto.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public class Book_basic {
    public Book_basic() {
    }

    private List<String> book_names = Arrays.asList(
            "해커스 토익 왕기초",
            "해커스 토익 스타트",
            "해커스 토익 기출보카"
    );

    private List<String> book_img = Arrays.asList(
            "https://search.pstatic.net/sunny/?src=https%3A%2F%2Fimage.aladin.co.kr%2Fproduct%2F21614%2F74%2Fcover500%2Fk222636593_2.jpg&type=a340",
            "https://search.pstatic.net/sunny/?src=http%3A%2F%2Fth3.tmon.kr%2Fthumbs%2Fimage%2Fd01%2F5a4%2Ff3a%2F8c2256470_700x700_95_FIT.jpg&type=sc960_832",
            "https://image.yes24.com/goods/67362730/XL"
    );
    private List<String> id = Arrays.asList(
            "1","2","3"
    );
    /*
    public List<byte[]> getBookImagesAsByteArray() throws IOException {
        List<byte[]> bookImages = new ArrayList<>();

        for (String imagePath : book_img) {
            byte[] imageBytes = readImageToByteArray(imagePath);
            bookImages.add(imageBytes);
        }

        return bookImages;
    }
    private byte[] readImageToByteArray(String imagePath) throws IOException {
        // 이미지 파일을 읽어와서 바이트 배열로 변환
        byte[] imageBytes;
        try {
            imageBytes = Files.readAllBytes(ResourceUtils.getFile(imagePath).toPath());
        } catch (IOException e) {
            throw new IOException("Error reading image file: " + imagePath, e);
        }

        return imageBytes;
    }*/

}
