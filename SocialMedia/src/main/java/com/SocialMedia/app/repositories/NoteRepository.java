package com.SocialMedia.app.repositories;

import com.SocialMedia.app.models.Note;
import org.springframework.data.repository.CrudRepository;

public interface NoteRepository extends CrudRepository<Note, Long> {
}
