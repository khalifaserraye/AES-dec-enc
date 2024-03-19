const crypto = require('crypto');
require('dotenv').config();

function aesEncrypt(data) {
    const secretKey = process.env.SECRET_KEY// "0123456789abcdef0123456789abcdef"; // Example secret key, replace with your actual key
    const iv = Buffer.alloc(16); // Initialization vector
    const cipher = crypto.createCipheriv('aes-256-cbc', secretKey, iv);
    let encryptedData = cipher.update(data, 'utf-8', 'base64');
    encryptedData += cipher.final('base64');
    return encryptedData;
}

const plaintext = "numEtudiant2136985";


const encryptedText = aesEncrypt(plaintext);
console.log('Encrypted Text:', encryptedText);