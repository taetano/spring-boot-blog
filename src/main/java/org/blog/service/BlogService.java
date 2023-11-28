package org.blog.service;

import lombok.RequiredArgsConstructor;
import org.blog.domain.Article;
import org.blog.dto.AddArticleRequest;
import org.blog.dto.UpdateArticleRequest;
import org.blog.repository.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public Article save(final AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    public Article findById(final long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }
    public void delete(final long id) {
        blogRepository.deleteById(id);
    }

    @Transactional
    public Article update(long id, UpdateArticleRequest request) {
        Article savedArticle = findById(id);
        savedArticle.update(request.getTitle(), request.getContent());

        return savedArticle;
    }
}
