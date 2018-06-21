<#assign base = "$">
<#assign base = base + "{base}">

-----  api.js 中代码
export const get${data.className}List = params => { return axios.post(`${base}/${data.varName}/list`,  params).then(res => res.data); };
export const remove${data.className} = params => { return axios.post(`${base}/${data.varName}/remove`, params ).then(res => res.data); };
export const batchRemove${data.className} = params => { return axios.post(`${base}/${data.varName}/batchremove`, params ).then(res => res.data); };
export const edit${data.className} = params => { return axios.post(`${base}/${data.varName}/edit`, params ).then(res => res.data); };
export const add${data.className} = params => { return axios.post(`${base}/${data.varName}/add`,  params ).then(res => res.data); };
