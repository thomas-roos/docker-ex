#!/usr/bin/env python3

import argparse
import sys
from http.server import HTTPServer, BaseHTTPRequestHandler


class EchoHandler(BaseHTTPRequestHandler):
    def do_GET(self):
        self._handle_request()
    
    def do_POST(self):
        self._handle_request()
    
    def do_PUT(self):
        self._handle_request()
    
    def do_DELETE(self):
        self._handle_request()
    
    def do_PATCH(self):
        self._handle_request()
    
    def _handle_request(self):
        try:
            content_length = int(self.headers.get('Content-Length', 0))
            if content_length > 1024 * 1024:  # 1MB limit
                self.send_error(413, "Request too large")
                return
            
            body = self.rfile.read(content_length).decode('utf-8', errors='ignore')
            
            print(f"{self.command} {self.path} - Body length: {len(body)}")
            
            self.send_response(200)
            self.send_header('Content-Type', 'text/plain')
            self.end_headers()
            self.wfile.write(body.encode('utf-8'))
            
        except Exception as e:
            print(f"Error handling request: {e}")
            self.send_error(500, "Internal server error")


def main():
    parser = argparse.ArgumentParser(description='HTTP Echo Server')
    parser.add_argument('--host', default='0.0.0.0', help='Host to bind to')
    parser.add_argument('--port', type=int, default=8080, help='Port to bind to')
    args = parser.parse_args()
    
    server = HTTPServer((args.host, args.port), EchoHandler)
    print(f"Echo server starting on {args.host}:{args.port}")
    
    try:
        server.serve_forever()
    except KeyboardInterrupt:
        print("\nShutting down server...")
        server.shutdown()
        sys.exit(0)


if __name__ == '__main__':
    main()
