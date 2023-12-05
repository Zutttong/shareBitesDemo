import axios from "axios"

export const getImage = (file) => {
    const forms = new FormData()
    forms.append('smfile', file)
    return new Promise((resolove, rejcet) => {
        axios.post('/uploadImage/upload', forms, {
            headers: {
                "Authorization": "3AyxTrfz4NCE8YxjBYTVlxpbiEdcj1yw",
                'Content-Type': 'multipart/form-data'
            }
        }).then(res => {
            resolove(res.data.images || res.data.url)
        })
    })
}