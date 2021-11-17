import api from './Api';
import qs from 'qs';

const baseUrl = '/api/board/comment';

export const boardCommentApi = {
    insertComment,
}

function insertComment(params) {
    return api.post(baseUrl, { ...params });
}