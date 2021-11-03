import api  from './Api';
import qs from 'qs';

const baseUrl = '/api/board';

export const boardApi = {
    getPostList,
}

function getPostList(params) {
    return api.get(baseUrl, {
        params: params,
        paramsSerializer: function (params) {
            return qs.stringify(params, { arrayFormat: 'indices', allowDots: true });
        }
    });
}