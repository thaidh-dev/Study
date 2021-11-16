import cv2
import mediapipe as mp
import time


class handDetector():
    def __init__(self, mode=False, maxHands=2, detectionCon=0.5, trackCon=0.5):
        self.mode = mode
        self.maxHands = maxHands
        self.detectionCon = detectionCon
        self.trackCon = trackCon

        self.mpHands = mp.solutions.hands
        self.hands = self.mpHands.Hands(self.mode, self.maxHands, self.detectionCon, self.trackCon)
        self.mpDraw = mp.solutions.drawing_utils

    def findHands(self, img, draw=True):
        # rgb là sử dụng 3 màu sắc cơ bản R(red - đỏ), G(green - xanh lục) và B(blue - xanh lam) để biểu diễn tất cả các màu sắc.
        # OpenCV đảo 2 kênh R và B, trở thành BGR
        imgRGB = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)
        # Processes an RGB image and returns the hand landmarks and handedness of each detected hand.
        self.results = self.hands.process(imgRGB)

        # "multi_hand_landmarks" field that contains the hand landmarks on each detected hand
        if self.results.multi_hand_landmarks:
            for handLms in self.results.multi_hand_landmarks:
                if draw:
                    # đánh dấu 21 điểm landmark trên bàn tay và nối các điểm lại vs nhau (mpHands.HAND_CONNECTIONS)
                    self.mpDraw.draw_landmarks(img, handLms, self.mpHands.HAND_CONNECTIONS)

    def findPosition(self, img, handNo=0, draw=True, lmPoint=4):
        if self.results.multi_hand_landmarks:
            # self.results.multi_hand_landmarks: tương đương vs nhiều tay
            # myHand.landmark: list các landmark trên 1 tay
            myHand = self.results.multi_hand_landmarks[handNo]
            for id, lm in enumerate(myHand.landmark):
                # chiều dài, rộng, cao của video frame đc mở ra
                h, w, c = img.shape
                # tọa độ của 21 điểm landmark trên bàn tay
                cx, cy = int(lm.x * w), int(lm.y * h)
                # vẽ điểm landmark(chỉnh màu, độ to, ...) ở vị trí chỉ định (mặc định là đầu ngón cái)
                # 21 điểm Hand Landmark: https://google.github.io/mediapipe/solutions/hands.html#hand-landmark-model
                if id == lmPoint and draw:
                    cv2.circle(img, (cx, cy), 7, (255, 0, 0), cv2.FILLED)
                    print(id, cx, cy)


def main():
    cTime = 0
    pTime = 0
    # mở camera, sử dụng camera 0 của máy
    cap = cv2.VideoCapture(0)
    detector = handDetector()

    while cap.isOpened():
        # img: video frame
        success, img = cap.read()
        detector.findHands(img)
        detector.findPosition(img)

        # tính fps
        cTime = time.time()
        fps = 1 / (cTime - pTime)
        pTime = cTime

        # viết chữ lên video frame được mở ra (viết fps)
        cv2.putText(img, str(int(fps)), (10, 70), cv2.FONT_HERSHEY_PLAIN, 3, (255, 0, 255), 3)
        # show video frame vs titlle 'Image'
        cv2.imshow("Image", img)
        cv2.waitKey(1)


if __name__ == "__main__":
    main()
