import React, {useEffect, useState} from 'react';
import Cookies from 'universal-cookie';

const App = () => {

    // POST method implementation:
    async function postData(url = '', data = {}) {
        // Default options are marked with *
        const response = await fetch(url, {
            method: 'POST', // *GET, POST, PUT, DELETE, etc.
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data) // body data type must match "Content-Type" header
        });

        return await response.json(); // parses JSON response into native JavaScript objects
    }

    const [urlInput, setUrlInput] = useState('');
    const [cookies] = useState(new Cookies());
    const [urlList, setUrlList] = useState([]);

    useEffect(() => {
        const urlCookies = cookies.get('urlList', true);
        if (urlCookies) {
            setUrlList(urlCookies);
        }
    }, []);

    useEffect(() => cookies.set('urlList', JSON.stringify(urlList)), [urlList]);

    const getShortUrl = async (e) => {
        e.preventDefault();

        if (urlList.find(pair => pair.longUrl === urlInput)) {
            return;
        }

        let result;
        try {
            result = await postData('/getShortUrl', {longUrl: urlInput});
        } catch (e) {
            alert('Error Connecting to Server');
        }
        const pair = {longUrl: urlInput, shortUrl: result.shortUrl};
        setUrlList([pair, ...urlList]);
    };

    const removePair = (pairID) => {
        const filteredList = urlList.filter((item, idx) => idx !== pairID);
        setUrlList(filteredList);
    };

    const textToClipboard = (text) => {
        const dummy = document.createElement('textarea');
        document.body.appendChild(dummy);
        dummy.value = text;
        dummy.select();
        document.execCommand('copy');
        document.body.removeChild(dummy);
    };

    const copyUrlToClipboard = (pairID) => {
        const shortUrl = window.location.href + urlList[pairID].shortUrl;

        const tooltipText = document.getElementsByClassName('tooltiptext')[0];
        tooltipText.innerHTML = 'Copied';

        textToClipboard(shortUrl);
    };

    const showToolTip = () => {
        const tooltipText = document.getElementsByClassName('tooltiptext')[0];
        tooltipText.style.visibility = 'visible';
    };

    const hideToolTip = () => {
        const tooltipText = document.getElementsByClassName('tooltiptext')[0];
        tooltipText.style.visibility = 'hidden';
        tooltipText.innerHTML = 'Copy';
    };

    return (
        <div className={'main'}>
            <h1 className={'title'}>URL SHORTNER</h1>
            <div className={'input-container'}>
                <form onSubmit={getShortUrl} className={'url-input-form'}>
                    <input className={'url-input'} value={urlInput} onChange={e => setUrlInput(e.target.value)}/>
                    <button type={'submit'} className={'shorten-url-button'}>Shorten Url</button>
                </form>
            </div>
            <div className={'user-urls'}>
                {urlList.map((pair, idx) => (
                    <div className={'url-pair'} key={idx}>
                        <a onClick={() => window.open('https://' + pair.longUrl)}
                           className={'long-url'}>{pair.longUrl}</a>
                        <div className={'url-link-container'}>
                            <div className="tooltip"
                                 onMouseEnter={() => showToolTip()}
                                 onMouseLeave={() => hideToolTip()}>
                                <a onClick={() => copyUrlToClipboard(idx)}>{pair.shortUrl}</a>
                                <span className="tooltiptext">Copy</span>
                            </div>
                            <p onClick={() => removePair(idx)} className="close">X</p>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default App;