import api from './Api';
import qs from 'qs';

const baseUrl = '/api/board/comment';

export const BoardCommentApi = {
    insertComment,
    insertChildComment,
    getChildCommentList
}

function insertComment(params) {
    return api.post(baseUrl, params);
}

function insertChildComment(params) {
    return api.post(baseUrl, params);
}

function getChildCommentList(seq) {
    return api.get(baseUrl + "/child/" + seq, {
        params: seq,
        paramsSerializer: function (params) {
            return qs.stringify(params, { arrayFormat: 'indices', allowDots: true });
        }
    });
}