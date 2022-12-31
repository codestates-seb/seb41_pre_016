import create from "zustand";
import {persist} from "zustand/middleware";
import axios from "axios";

export const loginStore = create(persist((set) => ({
    email: '',
    password: '',
    setEmail: (data) => set({email: data}),
    setPassword: (data) => set({password: data}),
    jwtStore:null,
    loginError:null,
    loginPost: async (url,data) => {
            await axios
                .post(url,data)
                .then((response) => {
                    set({ loginError: false });
                    set({ jwtStore: {
                            Authorization:response.headers.authorization,
                            Refresh:response.headers.refresh
                        } });
                    alert("성공!")
                })
                .catch((err) => {
                    set({ loginError: true });
                    console.log(err.response.status)
                    alert("실패!")
                })
    },
    isLogin: false,
    setLogin: (data) => set({isLogin: data}),
})));