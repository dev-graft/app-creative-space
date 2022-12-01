import axios, {AxiosInstance, AxiosRequestConfig, AxiosResponse} from 'axios'
// https://github.com/PCloud63514/react-native-common-module/blob/master/modules/network/index.tsx
const Api: AxiosInstance = axios.create({
    baseURL: 'http://localhost:8080/api'
});

Api.defaults.timeout = 300000;
Api.defaults.headers.post['Content-Type'] = 'application/json';
Api.interceptors.request.use(
    (config: AxiosRequestConfig) => config, // handleRequest
    (error: any) => Promise.reject(error) // handleRequestError
);
Api.interceptors.response.use(
    (response: AxiosResponse) => handleResponse(response), //handleResponse
    (error: any) => handleResponseError(error) //handleResponseError
);

const handleResponse = (response: AxiosResponse) => {
    return response
};

const handleResponseError = (error: any) => {
    return error
};

declare global {
    interface ApiResponse<T = any> {
        data: T,
        message: string,
        error?: string,
        success: boolean,
        timestamp: Date
    }
}

export const GET = <T = any, R = ApiResponse<T>>(url: string, config?: AxiosRequestConfig<any>): Promise<AxiosResponse<R>> => {
    return Api.get(url, config)
};
export const POST = <T = any, D = any, R = ApiResponse<T>>(url: string, data?: D, config?: AxiosRequestConfig<any>): Promise<R> => {
    return Api.post(url, data, config)
};
export const DELETE = <T = any, R = ApiResponse<T>>(url: string, config?: AxiosRequestConfig<any>): Promise<R> => {
    return Api.delete(url, config)
};