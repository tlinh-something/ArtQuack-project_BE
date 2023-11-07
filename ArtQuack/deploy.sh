echo "Building app..."
./mvnw clean package

echo "Deploy files to server..."
scp -r -i "C:\Users\DELL\OneDrive\Máy tính\art" target/art-be.jar root@167.172.92.40:/var/www/art-be

ssh -i "C:\Users\DELL\OneDrive\Máy tính\art" root@167.172.92.40 <<EOF
pid=\$(sudo lsof -t -i :8080)

if [ -z "\$pid" ]; then
    echo "Start server..."
else
    echo "Restart server..."
    sudo kill -9 "\$pid"
fi
cd /var/www/art-be
java -jar art-be.jar
EOF
exit
echo "Done!"