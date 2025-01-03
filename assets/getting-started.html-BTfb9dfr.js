import{_ as m,c as v,a as o,e as t,f as p,i as c,r as u,o as g,b as s,d as a,t as i}from"./app-DuW-mWuI.js";const b={__name:"getting-started.html",setup(r,{expose:n}){n();const e={version:c("version"),inject:c};return Object.defineProperty(e,"__isScriptSetup",{enumerable:!1,value:!0}),e}},y={class:"language-xml","data-highlighter":"prismjs","data-ext":"xml","data-title":"xml"},f={class:"line"},h={class:"language-groovy","data-highlighter":"prismjs","data-ext":"groovy","data-title":"groovy"},q={class:"line"},w={class:"token string"},x={class:"language-groovy","data-highlighter":"prismjs","data-ext":"groovy","data-title":"groovy"},S={class:"line"},j={class:"token string"};function M(r,n,d,e,O,V){const l=u("CodeGroupItem"),k=u("CodeGroup");return g(),v("div",null,[n[31]||(n[31]=o('<h1 id="快速开始" tabindex="-1"><a class="header-anchor" href="#快速开始"><span>快速开始</span></a></h1><p>我们通过一个简单的Demo来介绍如何使用 <a href="https://github.com/guoshiqiufeng/spring-cloud-stream-redis" target="_blank" rel="noopener noreferrer">spring-cloud-stream-redis</a> 的功能。</p><h2 id="初始化" tabindex="-1"><a class="header-anchor" href="#初始化"><span>初始化</span></a></h2><p>创建一个空的Spring Boot 工程，这里我们使用 3.2.0 版本。</p><h2 id="添加依赖" tabindex="-1"><a class="header-anchor" href="#添加依赖"><span>添加依赖</span></a></h2>',5)),t(k,null,{default:p(()=>[t(l,{title:"Maven",active:""},{default:p(()=>[s("div",y,[s("pre",null,[s("code",null,[n[3]||(n[3]=s("span",{class:"line"},[s("span",{class:"token tag"},[s("span",{class:"token tag"},[s("span",{class:"token punctuation"},"<"),a("dependency")]),s("span",{class:"token punctuation"},">")])],-1)),n[4]||(n[4]=a(`
`)),n[5]||(n[5]=s("span",{class:"line"},[a("    "),s("span",{class:"token tag"},[s("span",{class:"token tag"},[s("span",{class:"token punctuation"},"<"),a("groupId")]),s("span",{class:"token punctuation"},">")]),a("io.github.guoshiqiufeng.cloud"),s("span",{class:"token tag"},[s("span",{class:"token tag"},[s("span",{class:"token punctuation"},"</"),a("groupId")]),s("span",{class:"token punctuation"},">")])],-1)),n[6]||(n[6]=a(`
`)),n[7]||(n[7]=s("span",{class:"line"},[a("    "),s("span",{class:"token tag"},[s("span",{class:"token tag"},[s("span",{class:"token punctuation"},"<"),a("artifactId")]),s("span",{class:"token punctuation"},">")]),a("spring-cloud-starter-stream-redis"),s("span",{class:"token tag"},[s("span",{class:"token tag"},[s("span",{class:"token punctuation"},"</"),a("artifactId")]),s("span",{class:"token punctuation"},">")])],-1)),n[8]||(n[8]=a(`
`)),s("span",f,[n[0]||(n[0]=a("    ")),n[1]||(n[1]=s("span",{class:"token tag"},[s("span",{class:"token tag"},[s("span",{class:"token punctuation"},"<"),a("version")]),s("span",{class:"token punctuation"},">")],-1)),a(i(e.version),1),n[2]||(n[2]=s("span",{class:"token tag"},[s("span",{class:"token tag"},[s("span",{class:"token punctuation"},"</"),a("version")]),s("span",{class:"token punctuation"},">")],-1))]),n[9]||(n[9]=a(`
`)),n[10]||(n[10]=s("span",{class:"line"},[s("span",{class:"token tag"},[s("span",{class:"token tag"},[s("span",{class:"token punctuation"},"</"),a("dependency")]),s("span",{class:"token punctuation"},">")])],-1)),n[11]||(n[11]=a(`
`)),n[12]||(n[12]=s("span",{class:"line"},null,-1))])])])]),_:1}),t(l,{title:"Gradle (Short)",active:""},{default:p(()=>[s("div",h,[s("pre",null,[s("code",null,[s("span",q,[n[13]||(n[13]=a("implementation ")),s("span",w,"'io.github.guoshiqiufeng.cloud:spring-cloud-starter-stream-redis:"+i(e.version)+"'",1)]),n[14]||(n[14]=a(`
`)),n[15]||(n[15]=s("span",{class:"line"},null,-1))])])])]),_:1}),t(l,{title:"Gradle"},{default:p(()=>[s("div",x,[s("pre",null,[s("code",null,[s("span",S,[n[16]||(n[16]=a("implementation group")),n[17]||(n[17]=s("span",{class:"token punctuation"},":",-1)),n[18]||(n[18]=a()),n[19]||(n[19]=s("span",{class:"token string"},"'io.github.guoshiqiufeng.cloud'",-1)),n[20]||(n[20]=s("span",{class:"token punctuation"},",",-1)),n[21]||(n[21]=a(" name")),n[22]||(n[22]=s("span",{class:"token punctuation"},":",-1)),n[23]||(n[23]=a()),n[24]||(n[24]=s("span",{class:"token string"},"'spring-cloud-starter-stream-redis'",-1)),n[25]||(n[25]=s("span",{class:"token punctuation"},",",-1)),n[26]||(n[26]=a(" version")),n[27]||(n[27]=s("span",{class:"token punctuation"},":",-1)),n[28]||(n[28]=a()),s("span",j,"'"+i(e.version)+"'",1)]),n[29]||(n[29]=a(`
`)),n[30]||(n[30]=s("span",{class:"line"},null,-1))])])])]),_:1})]),_:1}),n[32]||(n[32]=o(`<h2 id="配置" tabindex="-1"><a class="header-anchor" href="#配置"><span>配置</span></a></h2><p>在 application.yml 中添加配置：</p><div class="language-yaml line-numbers-mode" data-highlighter="prismjs" data-ext="yml" data-title="yml"><pre><code><span class="line"><span class="token key atrule">spring</span><span class="token punctuation">:</span></span>
<span class="line">  <span class="token key atrule">cloud</span><span class="token punctuation">:</span></span>
<span class="line">    <span class="token key atrule">function</span><span class="token punctuation">:</span></span>
<span class="line">      <span class="token comment">#      definition: send;test</span></span>
<span class="line">      <span class="token key atrule">definition</span><span class="token punctuation">:</span> send</span>
<span class="line">    <span class="token key atrule">stream</span><span class="token punctuation">:</span></span>
<span class="line">      <span class="token key atrule">default-binder</span><span class="token punctuation">:</span> redis</span>
<span class="line">      <span class="token key atrule">binders</span><span class="token punctuation">:</span></span>
<span class="line">        <span class="token key atrule">redis</span><span class="token punctuation">:</span></span>
<span class="line">          <span class="token key atrule">type</span><span class="token punctuation">:</span> redis</span>
<span class="line">      <span class="token key atrule">redis</span><span class="token punctuation">:</span></span>
<span class="line">        <span class="token key atrule">binder</span><span class="token punctuation">:</span></span>
<span class="line">          <span class="token key atrule">configuration</span><span class="token punctuation">:</span></span>
<span class="line">            <span class="token key atrule">host</span><span class="token punctuation">:</span> 127.0.0.1</span>
<span class="line">            <span class="token key atrule">port</span><span class="token punctuation">:</span> <span class="token number">6379</span></span>
<span class="line">            <span class="token key atrule">password</span><span class="token punctuation">:</span> <span class="token number">123456</span></span>
<span class="line">            <span class="token key atrule">database</span><span class="token punctuation">:</span> <span class="token number">7</span></span>
<span class="line">          <span class="token key atrule">support-type</span><span class="token punctuation">:</span> queue_channel</span>
<span class="line">      <span class="token comment">#        bindings:</span></span>
<span class="line">      <span class="token comment">#          send-in-0:</span></span>
<span class="line">      <span class="token comment">#            consumer:</span></span>
<span class="line">      <span class="token comment">#              destination-is-pattern: true</span></span>
<span class="line">      <span class="token key atrule">bindings</span><span class="token punctuation">:</span></span>
<span class="line">        <span class="token key atrule">out-0</span><span class="token punctuation">:</span></span>
<span class="line">          <span class="token key atrule">destination</span><span class="token punctuation">:</span> test<span class="token punctuation">-</span>topic</span>
<span class="line">          <span class="token key atrule">content-type</span><span class="token punctuation">:</span> text/plain</span>
<span class="line">          <span class="token key atrule">group</span><span class="token punctuation">:</span> push<span class="token punctuation">-</span>producer<span class="token punctuation">-</span>group</span>
<span class="line">        <span class="token key atrule">send-in-0</span><span class="token punctuation">:</span></span>
<span class="line">          <span class="token key atrule">destination</span><span class="token punctuation">:</span> test<span class="token punctuation">-</span>topic</span>
<span class="line">          <span class="token key atrule">content-type</span><span class="token punctuation">:</span> text/plain</span>
<span class="line">          <span class="token key atrule">group</span><span class="token punctuation">:</span> test<span class="token punctuation">-</span>send<span class="token punctuation">-</span>group</span>
<span class="line"></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div></div></div><h2 id="编码" tabindex="-1"><a class="header-anchor" href="#编码"><span>编码</span></a></h2><p>编写实体类 MessageVO.java</p><div class="language-java line-numbers-mode" data-highlighter="prismjs" data-ext="java" data-title="java"><pre><code><span class="line"></span>
<span class="line"><span class="token annotation punctuation">@Data</span></span>
<span class="line"><span class="token keyword">public</span> <span class="token keyword">class</span> <span class="token class-name">MessageVO</span> <span class="token keyword">implements</span> <span class="token class-name">Serializable</span> <span class="token punctuation">{</span></span>
<span class="line"></span>
<span class="line">    <span class="token keyword">private</span> <span class="token keyword">static</span> <span class="token keyword">final</span> <span class="token keyword">long</span> serialVersionUID <span class="token operator">=</span> <span class="token number">807173843169199376L</span><span class="token punctuation">;</span></span>
<span class="line"></span>
<span class="line">    <span class="token keyword">private</span> <span class="token class-name">String</span> msg<span class="token punctuation">;</span></span>
<span class="line"></span>
<span class="line">    <span class="token keyword">private</span> <span class="token class-name">String</span> key<span class="token punctuation">;</span></span>
<span class="line"></span>
<span class="line">    <span class="token keyword">private</span> <span class="token class-name">Set</span><span class="token generics"><span class="token punctuation">&lt;</span><span class="token class-name">String</span><span class="token punctuation">&gt;</span></span> ids<span class="token punctuation">;</span></span>
<span class="line"></span>
<span class="line">    <span class="token annotation punctuation">@DateTimeFormat</span><span class="token punctuation">(</span>pattern <span class="token operator">=</span> <span class="token string">&quot;yyyy-MM-dd HH:mm:ss&quot;</span><span class="token punctuation">)</span></span>
<span class="line">    <span class="token annotation punctuation">@JsonFormat</span><span class="token punctuation">(</span>pattern <span class="token operator">=</span> <span class="token string">&quot;yyyy-MM-dd HH:mm:ss&quot;</span><span class="token punctuation">)</span></span>
<span class="line">    <span class="token keyword">private</span> <span class="token class-name">LocalDateTime</span> createTime<span class="token punctuation">;</span></span>
<span class="line"><span class="token punctuation">}</span></span>
<span class="line"></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div></div></div><p>编写监听类 <code>MessageHandler.java</code></p><div class="language-java line-numbers-mode" data-highlighter="prismjs" data-ext="java" data-title="java"><pre><code><span class="line"></span>
<span class="line"><span class="token annotation punctuation">@Slf4j</span></span>
<span class="line"><span class="token annotation punctuation">@Component</span><span class="token punctuation">(</span><span class="token string">&quot;send&quot;</span><span class="token punctuation">)</span></span>
<span class="line"><span class="token keyword">public</span> <span class="token keyword">class</span> <span class="token class-name">MessageHandler</span> <span class="token keyword">implements</span> <span class="token class-name">Consumer</span><span class="token generics"><span class="token punctuation">&lt;</span><span class="token class-name">Message</span><span class="token punctuation">&lt;</span><span class="token class-name">String</span><span class="token punctuation">&gt;</span><span class="token punctuation">&gt;</span></span> <span class="token punctuation">{</span></span>
<span class="line"></span>
<span class="line">    <span class="token doc-comment comment">/**</span>
<span class="line">     * Performs this operation on the given argument.</span>
<span class="line">     *</span>
<span class="line">     * <span class="token keyword">@param</span> <span class="token parameter">messageVOMessage</span> the input argument</span>
<span class="line">     */</span></span>
<span class="line">    <span class="token annotation punctuation">@Override</span></span>
<span class="line">    <span class="token keyword">public</span> <span class="token keyword">void</span> <span class="token function">accept</span><span class="token punctuation">(</span><span class="token class-name">Message</span><span class="token generics"><span class="token punctuation">&lt;</span><span class="token class-name">String</span><span class="token punctuation">&gt;</span></span> messageVOMessage<span class="token punctuation">)</span> <span class="token punctuation">{</span></span>
<span class="line">        log<span class="token punctuation">.</span><span class="token function">info</span><span class="token punctuation">(</span><span class="token string">&quot;send Receive New Messages: {}&quot;</span><span class="token punctuation">,</span> messageVOMessage<span class="token punctuation">.</span><span class="token function">getPayload</span><span class="token punctuation">(</span><span class="token punctuation">)</span><span class="token punctuation">)</span><span class="token punctuation">;</span></span>
<span class="line">    <span class="token punctuation">}</span></span>
<span class="line"><span class="token punctuation">}</span></span>
<span class="line"></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div></div></div><h2 id="发送消息" tabindex="-1"><a class="header-anchor" href="#发送消息"><span>发送消息</span></a></h2><p>添加测试Controller类，进行功能测试：</p><div class="language-java line-numbers-mode" data-highlighter="prismjs" data-ext="java" data-title="java"><pre><code><span class="line"></span>
<span class="line"><span class="token annotation punctuation">@Slf4j</span></span>
<span class="line"><span class="token annotation punctuation">@RestController</span></span>
<span class="line"><span class="token keyword">public</span> <span class="token keyword">class</span> <span class="token class-name">TestController</span> <span class="token punctuation">{</span></span>
<span class="line"></span>
<span class="line">    <span class="token annotation punctuation">@Autowired</span></span>
<span class="line">    <span class="token keyword">private</span> <span class="token class-name">StreamBridge</span> streamBridge<span class="token punctuation">;</span></span>
<span class="line"></span>
<span class="line">    <span class="token annotation punctuation">@GetMapping</span><span class="token punctuation">(</span><span class="token string">&quot;/send&quot;</span><span class="token punctuation">)</span></span>
<span class="line">    <span class="token keyword">public</span> <span class="token class-name">String</span> <span class="token function">send</span><span class="token punctuation">(</span><span class="token punctuation">)</span> <span class="token punctuation">{</span></span>
<span class="line">        <span class="token class-name">MessageVO</span> messageVO <span class="token operator">=</span> <span class="token keyword">new</span> <span class="token class-name">MessageVO</span><span class="token punctuation">(</span><span class="token punctuation">)</span><span class="token punctuation">;</span></span>
<span class="line">        messageVO<span class="token punctuation">.</span><span class="token function">setKey</span><span class="token punctuation">(</span><span class="token constant">UUID</span><span class="token punctuation">.</span><span class="token function">randomUUID</span><span class="token punctuation">(</span><span class="token punctuation">)</span><span class="token punctuation">.</span><span class="token function">toString</span><span class="token punctuation">(</span><span class="token punctuation">)</span><span class="token punctuation">)</span><span class="token punctuation">;</span></span>
<span class="line">        messageVO<span class="token punctuation">.</span><span class="token function">setMsg</span><span class="token punctuation">(</span><span class="token string">&quot;hello &quot;</span><span class="token punctuation">)</span><span class="token punctuation">;</span></span>
<span class="line">        messageVO<span class="token punctuation">.</span><span class="token function">setIds</span><span class="token punctuation">(</span><span class="token class-name">Set</span><span class="token punctuation">.</span><span class="token function">of</span><span class="token punctuation">(</span><span class="token string">&quot;1&quot;</span><span class="token punctuation">,</span> <span class="token string">&quot;2&quot;</span><span class="token punctuation">)</span><span class="token punctuation">)</span><span class="token punctuation">;</span></span>
<span class="line">        messageVO<span class="token punctuation">.</span><span class="token function">setCreateTime</span><span class="token punctuation">(</span><span class="token class-name">LocalDateTime</span><span class="token punctuation">.</span><span class="token function">now</span><span class="token punctuation">(</span><span class="token punctuation">)</span><span class="token punctuation">)</span><span class="token punctuation">;</span></span>
<span class="line">        streamBridge<span class="token punctuation">.</span><span class="token function">send</span><span class="token punctuation">(</span><span class="token string">&quot;out-0&quot;</span><span class="token punctuation">,</span> <span class="token constant">JSON</span><span class="token punctuation">.</span><span class="token function">toJSONString</span><span class="token punctuation">(</span>messageVO<span class="token punctuation">,</span> <span class="token class-name">JSONWriter<span class="token punctuation">.</span>Feature<span class="token punctuation">.</span>WriteClassName</span><span class="token punctuation">)</span><span class="token punctuation">)</span><span class="token punctuation">;</span></span>
<span class="line">        <span class="token keyword">return</span> <span class="token string">&quot;success&quot;</span><span class="token punctuation">;</span></span>
<span class="line">    <span class="token punctuation">}</span></span>
<span class="line"></span>
<span class="line"><span class="token punctuation">}</span></span>
<span class="line"></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div></div></div><p>完整的代码示例 查看 <a href="https://github.com/guoshiqiufeng/spring-cloud-stream-redis-samples" target="_blank" rel="noopener noreferrer">spring-cloud-stream-redis-samples</a></p><h2 id="小结" tabindex="-1"><a class="header-anchor" href="#小结"><span>小结</span></a></h2><p>通过以上几个简单的步骤，我们就实现了 MessageVO 的发送和消费。</p>`,14))])}const I=m(b,[["render",M],["__file","getting-started.html.vue"]]),N=JSON.parse('{"path":"/guide/getting-started.html","title":"快速开始","lang":"zh-cn","frontmatter":{"lang":"zh-cn","title":"快速开始","description":null},"headers":[{"level":2,"title":"初始化","slug":"初始化","link":"#初始化","children":[]},{"level":2,"title":"添加依赖","slug":"添加依赖","link":"#添加依赖","children":[]},{"level":2,"title":"配置","slug":"配置","link":"#配置","children":[]},{"level":2,"title":"编码","slug":"编码","link":"#编码","children":[]},{"level":2,"title":"发送消息","slug":"发送消息","link":"#发送消息","children":[]},{"level":2,"title":"小结","slug":"小结","link":"#小结","children":[]}],"git":{"updatedTime":1735895876000,"contributors":[{"name":"yanghq","username":"yanghq","email":"1040926235@qq.com","commits":5,"url":"https://github.com/yanghq"}]},"filePathRelative":"guide/getting-started.md"}');export{I as comp,N as data};