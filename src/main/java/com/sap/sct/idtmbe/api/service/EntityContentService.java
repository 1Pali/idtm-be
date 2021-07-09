package com.sap.sct.idtmbe.api.service;

import com.sap.sct.idtmbe.model.entity.EntityContent;

import java.util.List;

public interface EntityContentService {

    /**
     * Get file by id
     *
     * @param id Long of file
     * @return file with given id
     */
    EntityContent getById(final Long id);

    /**
     * Get list of all file
     *
     * @return list of all file
     */
    List<EntityContent> getList();

    /**
     * delete file by id
     *
     * @param id Long of file
     */
    void delete(final Long id);

    /**
     * Update {@link EntityContent} with given values from {@link EntityContent}
     *
     * @param {@link UpdateFileDto}
     * @return updated file
     */
    EntityContent update(final Long fileId, final EntityContent dto);

    /**
     * Create {@link EntityContent} with given values from {@link EntityContent}
     *
     * @param {@link UpdateFileDto}
     * @return created file
     */
    EntityContent create(final EntityContent dto);

}