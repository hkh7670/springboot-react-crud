import api from './Api';
import qs from 'qs';

const baseUrl = '/api/board';

export const boardApi = {
    getPostList,
    getPostOne,
    insertComment,
}

function getPostList(params) {
    return api.get(baseUrl, {
        params: params,
        paramsSerializer: function (params) {
            return qs.stringify(params, { arrayFormat: 'indices', allowDots: true });
        }
    });
}

function getPostOne(seq) {
    return api.get(baseUrl + "/" + seq, {
        params: seq,
        paramsSerializer: function (params) {
            return qs.stringify(params, { arrayFormat: 'indices', allowDots: true });
        }
    });
}

function insertComment(params) {
    return api.post(baseUrl + "/comment", {
        params: params,
        paramsSerializer: function (params) {
            return qs.stringify(params, { arrayFormat: 'indices', allowDots: true });
        }
    });
}