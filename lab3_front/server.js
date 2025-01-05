const http = require('http');
    const fs = require('fs');
    const path = require('path');

    const hostname = 'localhost';
    const port = 3000;

    const server = http.createServer((req, res) => {
    let filePath = path.join(__dirname, 'public', req.url === '/' ? 'index.html' : req.url);

        if (req.url.endsWith('/')) {
            filePath = path.join(__dirname, 'public', req.url, 'index.html');
        }
        if(!fs.existsSync(filePath) && !filePath.includes('.')) {
            filePath = path.join(__dirname, 'public', req.url + '.html')
         }
        if (!fs.existsSync(filePath)) {
          filePath = path.join(__dirname, 'public', '404.html');
      }

        const extname = String(path.extname(filePath)).toLowerCase();
         let contentType = 'text/html';


         if (extname === '.js') {
           contentType = 'text/javascript';
         } else if (extname === '.css') {
           contentType = 'text/css';
        } else if (extname === '.png') {
           contentType = 'image/png';
         }

       fs.readFile(filePath, (err, content) => {
            if (err) {
                if (err.code === 'ENOENT') {
                    fs.readFile(path.join(__dirname, 'public', '404.html'), (error, content) => {
                        res.writeHead(404, { 'Content-Type': 'text/html' });
                       res.end(content, 'utf-8');
                    });
               } else {
                     res.writeHead(500);
                     res.end('Internal Server Error');
                 }
                return;
          }

          res.writeHead(200, { 'Content-Type': contentType });
             res.end(content, 'utf-8');
        });
   });

   server.listen(port, hostname, () => {
       console.log(`Server running at http://${hostname}:${port}/`);
  });