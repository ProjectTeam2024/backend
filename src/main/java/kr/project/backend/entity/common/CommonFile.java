package kr.project.backend.entity.common;

import jakarta.persistence.*;
import kr.project.backend.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.io.Serializable;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommonFile extends BaseTimeEntity implements Serializable {

    @Id
    @Comment(value = "파일키값")
    @Column(length = 10)
    private String fileId;

    @Comment(value = "파일명")
    @Column(length = 50)
    private String fileName;

    @Comment(value = "파일경로")
    @Column(length = 50)
    private String filePath;

    @Comment(value = "파일url")
    @Column(length = 100)
    private String fileUrl;

    public CommonFile(String fileId, String fileName, String filePath, String fileUrl){
        this.fileId = fileId;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileUrl = fileUrl;
    }

}
